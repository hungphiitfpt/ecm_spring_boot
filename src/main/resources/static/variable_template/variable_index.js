var host = `https://ecm-springboot-phinph.cleverapps.io/`;
var api_images = `https://ecm-springboot-phinph.cleverapps.io/api/v1/FileUpload/files/`;

let listIg = localStorage.setItem('images','');

// Toast function
function toast({ title = "", message = "", type = "info", duration = 3000 }) {
	const main = document.getElementById("toast");
	if (main) {
	  const toast = document.createElement("div");
  
	  // Auto remove toast
	  const autoRemoveId = setTimeout(function () {
		main.removeChild(toast);
	  }, duration + 1000);
  
	  // Remove toast when clicked
	  toast.onclick = function (e) {
		if (e.target.closest(".toast__close")) {
		  main.removeChild(toast);
		  clearTimeout(autoRemoveId);
		}
	  };
  
	  const icons = {
		success: "fas fa-check-circle",
		info: "fas fa-info-circle",
		warning: "fas fa-exclamation-circle",
		error: "fas fa-exclamation-circle"
	  };
	  const icon = icons[type];
	  const delay = (duration / 1000).toFixed(2);
  
	  toast.classList.add("toast_c", `toast--${type}`);
	  toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;
  
	  toast.innerHTML = `
					  <div class="toast__icon">
						  <i class="${icon}"></i>
					  </div>
					  <div class="toast__body">
						  <h3 class="toast__title">${title}</h3>
						  <p class="toast__msg">${message}</p>
					  </div>
					  <div class="toast__close">
						  <i class="fas fa-times"></i>
					  </div>
				  `;
	  main.appendChild(toast);
	}
  }

  function toastMessage(title__c,message__c,type__c,time__c) {
	toast({
	  title: title__c,
	  message: message__c,
	  type: type__c,
	  duration: time__c
	});
  }

  async function exportFile(page__v,size__v,key__v){
	page__v = $('.li-disable').data('page');
	size__v = $('.select-filter-table').val();
	key__v = $('.keyword-filter-table').val();

	console.log("page__v" ,page__v,"size__v" , size__v, "key__v",key__v);

	let method = 'get',

	url = `${host}api/products/export-excel`,

	params = { page: page__v ,size: size__v,keyword:key__v},

	data = {};

	var res = await axiosTemplate(method, url, params, data);
	window.location.assign(`${res.request.responseURL}`);
  }
  
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

