$(function(){

    $('#btnSubmit').click(function(e){
        e.preventDefault();

        const uid = $('#passCheckId').text();
        const pass = $('#passCheck').val();

        const jsonData = {"pass" : pass, "uid" : uid};

        $.ajax({
            url: '/my/infoPassConfirm',
            type: 'POST',
            data: jsonData, // 변수니까 '' 안씀
            dataType: 'json', // Type 대문자
            success: function(data){

                //alert('success : ' + data);

                if(data == 1){
                    myForm.submit();
                    alert('수정 완료했습니다.');
                }else{
                    alert('입력한 비밀번호가 일치하지 않습니다.');
                    return; // return 하면 무슨 효과??
                }
            }
        });
    });
});