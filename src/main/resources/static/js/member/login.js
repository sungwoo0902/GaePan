document.addEventListener('DOMContentLoaded', function() {
    var urlParams = new URLSearchParams(window.location.search);
    var successParam = urlParams.get('success');

    if (successParam === '100') {
        alert('아이디 또는 비밀번호를 확인해주세요.');
    } else if (successParam === '101') {
        alert('로그아웃되었습니다.');
    }
});