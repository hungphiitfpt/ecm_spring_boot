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
        <a href="" class="row d-flex align-items-center">
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