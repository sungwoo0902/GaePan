// 인증번호 발송 버튼 초기에 비활성화
$(document).ready(function () {
    $("#btnEmailAuth").prop('disabled', true);

    // 인증번호 발송 버튼 클릭 이벤트 처리
    $("#btnEmailAuth").click(function () {
        alert("인증번호가 발송되었습니다.")
        // 버튼 클릭 시 처리할 내용
        $(this).hide(); // 인증번호 발송 버튼 숨김
        $("#verificationCode").show(); // 인증번호 확인 입력창 보임
    });

    // 확인 버튼 클릭 시 처리할 내용
    $("#btnVerifyCode").click(function () {
        var authCode = $("#authCode").val();
        // 여기에서 인증번호 확인 로직을 추가할 수 있습니다.
    });
});