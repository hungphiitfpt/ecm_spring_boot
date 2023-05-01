$(document).ready(function(){
    $(".upload-area").click(function(){
        $('#upload-input').trigger('click');
    });

    $('#upload-input').change(event => {
        if(event.target.files){
            let listFile = [];
            let filesAmount = event.target.files.length;
            $('.upload-img').html("");
            for(let i = 0; i < filesAmount; i++){
                let reader = new FileReader();
                reader.onload = function(event){
                    let html = `
                        <div class = "uploaded-img">
                            <img src = "${event.target.result}" style="
                            max-width: 100px;
                            max-height: 100px;">
                            <button type = "button" class = "remove-btn">
                                <i class = "fas fa-times"></i>
                            </button>
                        </div>
                    `;
                    $(".upload-img").append(html);
                }
                reader.readAsDataURL(event.target.files[i]);
            }
            $('.upload-info-value').text(filesAmount);
            $('.upload-img').css('padding', "20px");
            let formData = new FormData();
            listFile = event.target.files; // Lấy file từ input có id là "fileInput"
            // Tạo đối tượng FormData
            listFile.forEach(img => {
                formData.append("file", img)
            })
            // Gọi API upload file với axios
            axios.post(`${host}api/v1/FileUpload/upload`, formData, {
                headers: {
                'Content-Type': 'multipart/form-data'
                }
            }).then(response => {
                console.log(response.data);
                localStorage.setItem('images',response.data);
            }).catch(error => {
                console.error(error);
            });
        }
    });
    $(window).click(function(event){
        if($(event.target).hasClass('remove-btn')){
            $(event.target).parent().remove();
        } else if($(event.target).parent().hasClass('remove-btn')){
            $(event.target).parent().parent().remove();
        }
    })
});
// async function ecm_create_product(){
//     let method = 'post',

// 	url = `${host}api/v1/FileUpload`,

// 	data = {};

//     params = {};

//     let res = await axiosTemplate(method, url, params, data);
// }