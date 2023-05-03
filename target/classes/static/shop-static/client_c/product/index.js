
$(function() {
    loadAllProduct();
})

$('#headerSearch').keypress(async function () { 
    let list_product_shop__c = '',
    key_word = $('#headerSearch').val(),
    image = '';
	let method = 'get',

		url = `${host}api/products/product2`,

		params = {keyword: key_word, page: 0 ,size: 1000},

		data = {};

    var res = await axiosTemplate(method, url, params, data,'body');

    for (let i = 0; i < res.data.content.length; i++) {
        if(res.data.content[i].images.length > 0) {
            image = `${host}api/v1/FileUpload/files/`+res.data.content[i].images[0].url
        } else {
            image = '';
        }
        list_product_shop__c += `
        <a href="${host}shop/product/${res.data.content[i].id}" class="row d-flex align-items-center">
        <div class="col-2 pd_0">
            <img src="${image}" alt="" class="card-img">
        </div>
        <div class="col-10 pd_r_0">
                <p class="mg_0">${res.data.content[i].name}</p>
                <p class="mg_0">Danh mục : ${res.data.content[i].category.name}</p>
        </div>
        </a>`
    }
    $('#result-search-header').html(list_product_shop__c);
})

async function loadAllProduct() {
    let list_product_shop__c = '';
    let image = '';
	let method = 'get',

		url = `${host}api/products/product`,

		params = { page: 0 ,size: 1000},

		data = {};

    var res = await axiosTemplate(method, url, params, data,'body');

    for (let i = 0; i < res.data.content.length; i++) {
        if(res.data.content[i].images.length > 0) {
            image = `${host}api/v1/FileUpload/files/`+res.data.content[i].images[0].url
        } else {
            image = '';
        }
        list_product_shop__c += `<div class="swiper-slide card">
        <div class="card-content">
          <div class="image">
            <img src="${image}" alt="" class="card-img">
          </div>

          <div class="media-icons">
            <i class="fab fa-facebook"></i>
            <i class="fab fa-twitter"></i>
            <i class="fab fa-github"></i>
          </div>

          <div class="name-profession">
            <span class="name">${res.data.content[i].name}</span>
            <span class="profession">${res.data.content[i].category.name}</span>
          </div>

          <div class="rating">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="far fa-star"></i>
            <i class="far fa-star"></i>
          </div>

          <div class="button">
            <button class="aboutMe">About Me</button>
            <button class="hireMe">Hire Me</button>
          </div>
        </div>
      </div>`
    }
    $('.list-product-shop').html(list_product_shop__c);
	
}


