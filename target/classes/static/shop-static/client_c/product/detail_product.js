const url = window.location.href;
const productId = url.split("/").pop();

$(function() {
    loadProduct();
})

async function loadProduct() {
    let image__c = '';
	let method = 'get',

		url = `${host}api/products/detail/${productId}`,

		params = {},

		data = {};

    var res = await axiosTemplate(method, url, params, data,'body');
    $('#category-name-shop').text(res.data.data.category.name);
    $('#name-product-shop').text(res.data.data.name)
}