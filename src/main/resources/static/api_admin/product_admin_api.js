
$(function() {
    loadAllProduct();
    loadAllCategory();
    loadAllColor();
})

async function ecm_create_product() {
	let method = 'post',

		url = `${host}api/products/add`,

		data = {
            name : $('.name_product__c').val(),
            categoryId : Math.round(parseFloat($('.id_category_select__c option:selected').val())), 
            description : $('.content_description__c').val(),
            colorId: Math.round(parseFloat($('.id_color_select__c option:selected').val())),
            price: $('.total_price__c').val()
    };

    params = {};

	  let res = await axiosTemplate(method, url, params, data);
    let id_just_create = res.data.id;
    addListImageForProduct(id_just_create);
    loadAllProduct();
}

async function addListImageForProduct(id) {
  let listIg = localStorage.getItem('images');

  let method = 'post',

		url = `${host}api/v1/FileUpload/add`,

		params = {
      productId: id,
      urls : listIg
    },

		data = {};

	let res = await axiosTemplate(method, url, params, data);
}

async function loadAllCategory() {
  let category = '';
	let method = 'get',

		url = `${host}api/category`,

		params = { page: 0 , size: 10000},

		data = {};

	let res = await axiosTemplate(method, url, params, data);
  for (let i = 0; i < res.data.content.length; i++) {
    category += `<option value="${res.data.content[i].id}">${res.data.content[i].name}</option>`;
  }

  $('.list-category-render').html(category);

}

async function loadAllColor() {
  let category = '';
	let method = 'get',

		url = `${host}api/color`,

		params = { page: 0 , size: 10000},

		data = {};

	let res = await axiosTemplate(method, url, params, data);
  for (let i = 0; i < res.data.content.length; i++) {
    category += `<option value="${res.data.content[i].id}">${res.data.content[i].name}</option>`;
  }
  $('.list-color-render').html(category);
}

async function loadAllProduct() {

	let method = 'get',

		url = `${host}api/products/product`,

		params = { page: 0 ,size: 10},

		data = {};

    var res = await axiosTemplate(method, url, params, data,'#table-list-product-manager');
    
    drawTableProductManager(res, $('#table-list-product-manager'))
	
}

async function selectFilterTableProduct(page__v,size__v,key__v) {
  // Xử lý logic khi người dùng click vào đường link
  console.log("Đã click vào trang " + page__v);
  let page__c = page__v;
  size__v = $('.select-filter-table').val();
  key__v = $('.keyword-filter-table').val();
	let method = 'get',

		url = `${host}api/products/product2`,

		params = { page: page__c ,size: size__v,keyword:key__v},

		data = {};

    var res = await axiosTemplate(method, url, params, data,'#table-list-product-manager');
    
    drawTableProductManager(res, $('#table-list-product-manager'))

    if(res.data.content.length == 0) {
      toastMessage("Có lỗi xảy ra","Không tìm thấy sản phẩm phù hợp","error",5000)
    }else {
      toastMessage("Thành công","Sản phẩm vừa được làm mới","success",5000)
    }
	
}

async function drawTableProductManager(res) {
	var ProductHTML = "",
      color = "",
      image = "",
      page__c = '',
      page_last='';
	for (let i = 0; i < res.data.content.length; i++) {
        switch(res.data.content[i].color.id) {
            case 1:
              color = 'danger';
              // statements to be executed when expression matches value1
              break;
            case 2:
                color = 'green';
              // statements to be executed when expression matches value2
              break;
            case 3:
                color = 'purple';
                // statements to be executed when expression matches value2
              break;
            case 4:
                color = 'yellow';
              // statements to be executed when expression matches value2
              break;
            default:
          }
          if(res.data.content[i].images.length > 0) {
            image = `${host}api/v1/FileUpload/files/`+res.data.content[i].images[0].url
          } else {
            image = '';
          }
        var price = formatVND(res.data.content[i].price);
		ProductHTML += `<tr class="accordion-toggle collapsed" id="c-${res.data.content[i].id}" data-toggle="collapse" data-parent="#c-${res.data.content[i].id}" href="#collap-${res.data.content[i].id}">
        <td>${res.data.content[i].id}</td>
        <td>${res.data.content[i].name}</td>
        <td><span class="badge badge-pill badge-success mr-2">S</span><small class="text-muted">Paid</small></td>
        <td>${price}</td>
        <td><button class="btn btn-sm dropdown-toggle more-horizontal" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="text-muted sr-only">Action</span>
          </button>
          <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#">Edit</a>
            <a class="dropdown-item" href="#">Remove</a>
            <a class="dropdown-item" href="#">Assign</a>
          </div>
        </td>
      </tr>
      <tr id="collap-${res.data.content[i].id}" class="collapse in p-3 bg-light">
        <td colspan="8">
        <div class="card profile shadow">
        <div class="card-body my-4">
          <div class="row align-items-center">
            <div class="col-md-3 text-center mb-5">
              <a href="${image}" class="avatar avatar-xl">
                <img src="${image}" alt="..." class="avatar-img rounded-circle">
              </a>
            </div>
            <div class="col">
              <div class="row align-items-center">
                <div class="col-md-7">
                  <h4 class="mb-1">Danh mục: ${res.data.content[i].category.name}</h4>
                  <p class="small mb-3"><span class="badge badge-${color}">Màu: ${res.data.content[i].color.name}</span></p>
                </div>
                <div class="col">
                </div>
              </div>
              <div class="row mb-4">
                <div class="col-md-7">
                  <p class="text-muted">
                    ${res.data.content[i].description}
                  </p>
                </div>
                <div class="col">
                  <p class="small mb-0 text-muted">Nec Urna Suscipit Ltd</p>
                  <p class="small mb-0 text-muted">P.O. Box 464, 5975 Eget Avenue</p>
                  <p class="small mb-0 text-muted">(537) 315-1481</p>
                </div>
              </div>
              <div class="row align-items-center">
                <div class="col-md-7 mb-2">
                  <span class="small text-muted mb-0">Ngày tạo: ${res.data.content[i].createdAt}</span>
                </div>
                <div class="col mb-2">
                  <button type="button" class="btn btn-primary">Message</button>
                  <button type="button" class="btn btn-secondary">Profile</button>
                </div>
              </div>
            </div>
          </div> 
        </div> 
      </div>
      </td>
      </tr>`;
	}
  for (let i = 1; i < res.data.totalPages; i++) {
    page__c += `
    <li class="page-item"><a class="page-link page-link${i}" href="#" onclick="selectFilterTableProduct(${i})">${i}</a></li>
    `
  }
  page_last = `<a class="page-link" href="#" onclick="selectFilterTableProduct(${res.data.totalPages - 1})">Next</a>`;
  $('.page_last__c').html(page_last);
	$('#table-list-product-manager').html(ProductHTML);
  $('.page_table__c').html(page__c);
  $('.page-item .page-link').removeClass('li-disable');
  console.log(res.data.number);
  if(res.data.number + 1 == res.data.totalPages){
    $('.page_last__c .page-link').addClass('li-disable');
  } else if (res.data.number == 0) {
    $('.page_first__c .page-link').addClass('li-disable');
  } else {
    console.log($('.page_table__c .page_item .page-link'));
    //abc
  }
}