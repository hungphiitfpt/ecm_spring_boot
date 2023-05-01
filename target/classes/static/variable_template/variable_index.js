var host = `http://localhost:8080/`;
var api_images = `http://localhost:8080/api/v1/FileUpload/files/`;
/*
var api_graduation = `http://localhost:8080/api/graduation/`;
var api_admin = `http://localhost:8080/api/admin/`;
var api_upload = `http://localhost:8080/api/v1/FileUpload`;

var errorNumber = 'Bạn phải nhập vào kiểu dữ liệu số';
var errorRequired = 'Trường này không được để trống';
sessionStorage.removeItem("image");
localStorage.setItem('currentPage', 0);
*/
async function axiosTemplate(method, url, params, data,class__c) {
	$(class__c).append(`<div class="mb-4" id="loading-api" style="
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);">
	  <div class="spinner-grow mr-3 text-primary" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-secondary" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-success" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-danger" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-warning" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-info" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-primary" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	  <div class="spinner-grow mr-3 text-dark" role="status">
		<span class="sr-only">Loading...</span>
	  </div>
	</div>`);
	let res = await axios({
		method: method, url: url, params: params, data: data
	});
	console.log(res);
	if (res.data.status == 200) {
		$('#loading-api').remove();
	}
	return res;
}
/*function sweatAlert(message, status) {
	const Toast = Swal.mixin({
		toast: true,
		position: 'top-end',
		showConfirmButton: false,
		timer: 1500,
		timerProgressBar: false,
		didOpen: (toast) => {
			toast.addEventListener('mouseenter', Swal.stopTimer)
			toast.addEventListener('mouseleave', Swal.resumeTimer)
		}
	})

	Toast.fire({
		icon: status,
		title: message

	})
}

function SweatAlertConfirmDelete() {
	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this imaginary file!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {

				swal("Poof! Your imaginary file has been deleted!", {
					icon: "success",
				});
			} else {
				swal("Your imaginary file is safe!");
			}
		});
}
function caculatorMoneyDiscount(money, discount) {
	let moneyAfter = money * [(100 - discount) / 100];
	return moneyAfter;
}
*/
function formatMoney(str) {
	return str.split('').reverse().reduce((prev, next, index) => {
		return ((index % 3) ? next : (next + ',')) + prev
	})
}

function formatVND(x) {
x = x.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
return x;
}


function formatDate(date) {
	var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [year, month, day].join('-');
}
async function api_getCountryVietnamese() {
	let method = 'get',
		url = `https://provinces.open-api.vn/api/?depth=2`,
		params = { depth: 1 },
		data = {};
	let res = await axiosTemplate(method, url, params, data);
}

var callAPI = (api) => {
	return axios.get(api).then((response) => {
		return response;
	});
}

