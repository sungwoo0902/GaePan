// 탈퇴하기
$('#btnWithdraw').click(function(e){

    let result = confirm("회원 탈퇴를 희망하시는게 맞나요?");
    if(result){
        return true;
    }else{
        return false;
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const userIdElement = document.getElementById('userId');

    // 아이디 마스킹
    const originalUserId = userIdElement.getAttribute('value'); // userIdElement의 value 속성 값을 originalUserId에 저장합니다.
    const maskedUserId = originalUserId.substring(0, 3) + "****"; // originalUserId의 처음 세 글자를 제외한 나머지를 "****"로 대체하여 maskedUserId에 저장합니다.

    // value 속성을 변경하여 마스킹된 값을 보여줍니다.
    userIdElement.setAttribute('value', maskedUserId);
});
