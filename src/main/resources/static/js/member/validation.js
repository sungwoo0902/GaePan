$(document).ready(function () {

    // 폼 데이터 검증 상태 객체
    let formValidation = {
        isUidOk: false,
        isPassOk1: false,
        isPassOk2: false,
        isNameOk: false,
        isEmailOk: false,
        isHpOk: false,
        isNickOk: false,
    };

    // 데이터 검증에 사용하는 정규표현식
    const reUid = /^[a-z]+[a-z0-9]{4,19}$/g;
    const rePass = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
    const reName = /^[가-힣]{2,10}$/;
    const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    const reHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

    // 입력 검증 함수
    function validateInput(input, regex, feedbackMessage, fieldName) {
        const value = input.val();
        const feedback = input.siblings('.invalid-feedback');

        if (value.match(regex)) {
            feedback.text('');
            input.removeClass('is-invalid');
            input.addClass('is-valid');
            formValidation[fieldName] = true;
        } else {
            feedback.css('color', 'red').text(feedbackMessage);
            input.removeClass('is-valid');
            input.addClass('is-invalid');
            formValidation[fieldName] = false;
        }
    }

    // 아이디 검사
    $('input[name=uid]').focusout(function () {
        const uidInput = $(this);
        const uid = uidInput.val();

        validateInput($(this), reUid, '사용 불가능한 아이디 입니다.', 'isUidOk');

        if (formValidation.isUidOk) {
            // 중복 확인 API 호출
            $.ajax({
                url: `/member/check/uid/${uid}`,
                type: 'get',
                statusCode: {
                    // 서버 응답 상태 코드에 따른 처리
                    200: function() {
                        uidInput.siblings('.invalid-feedback').css('color', 'green').text("사용 가능한 아이디입니다.");
                        uidInput.removeClass('is-invalid');
                        uidInput.addClass('is-valid');
                        formValidation.isUidOk = true;
                        console.log("Success: 200 OK");
                    },
                    409: function() {
                        uidInput.siblings('.invalid-feedback').css('color', 'red').text("이미 사용중인 아이디입니다.");
                        uidInput.removeClass('is-valid');
                        uidInput.addClass('is-invalid');
                        formValidation.isUidOk = false;
                        console.log("중복입니다. 409");
                    }
                },
                complete: function(xhr, status) {
                    // 요청이 완료된 후 실행되는 함수
                    console.log("Complete - Status Code:", xhr.status);
                }
            });
        }

        if (uid === '') {
            uidInput.removeClass('is-invalid');
            uidInput.removeClass('is-valid');
            uidInput.siblings('.invalid-feedback').text('');
            formValidation.isUidOk = false;
        }
    });

    // 비밀번호 검사
    function validatePassword(passInput, passFeedback, isPassVariable) {
        const pass = passInput.val();
        const feedback = passFeedback;
        const input = passInput;

        if (pass.match(rePass)) {
            feedback.css('color', 'green').text('사용할 수 있는 비밀번호 입니다.');
            input.removeClass('is-invalid');
            input.addClass('is-valid');
            formValidation[isPassVariable] = true;
        } else {
            feedback.css('color', 'red').text('사용할 수 없는 비밀번호 입니다.');
            input.addClass('is-invalid');
            formValidation[isPassVariable] = false;
        }
    }

    $('input[name=pass1]').focusout(function () {
        const pass1Input = $(this);
        const pass1 = pass1Input.val();

        validatePassword($(this), $(this).siblings('.invalid-feedback'), 'isPassOk1');

        if (pass1 === '') {
            pass1Input.removeClass('is-invalid');
            pass1Input.removeClass('is-valid');
            pass1Input.siblings('.invalid-feedback').text('');
            formValidation.isPassOk1 = false;
        }
    });

    $('input[name=pass2]').focusout(function () {
        const pass2Input = $(this);
        const pass2val = pass2Input.val();

        validatePassword($(this), $(this).siblings('.invalid-feedback'), 'isPassOk2');

        // pass2의 값을 따로 확인하여 유효성을 검사합니다.
        const pass1 = $('input[name=pass1]').val();
        const pass2 = $(this).val();
        const feedback = $(this).siblings('.invalid-feedback');

        if (pass2 === '' || pass1 !== pass2) {
            feedback.css('color', 'red').text('비밀번호가 일치하지 않습니다.');
            $(this).addClass('is-invalid');
            formValidation.isPassOk2 = false;
        } else {
            feedback.text('');
            $(this).removeClass('is-invalid');
            $(this).addClass('is-valid');
            formValidation.isPassOk2 = true;
        }

        if (pass2val === '') {
            pass2Input.removeClass('is-invalid');
            pass2Input.removeClass('is-valid');
            pass2Input.siblings('.invalid-feedback').text('');
            formValidation.isPassOk2 = false;
        }
    });

    // 이름 검사
    $('input[name=name]').focusout(function () {
        const nameInput = $(this);
        const name = nameInput.val();

        validateInput($(this), reName, '유효한 이름이 아닙니다.', 'isNameOk');

        if (name === '') {
            nameInput.removeClass('is-invalid');
            nameInput.removeClass('is-valid');
            nameInput.siblings('.invalid-feedback').text('');
            formValidation.isNameOk = false;
        }
    });

    // 닉네임 검사
    $('input[name=nick]').focusout(function () {
        const nickInput = $(this);
        const nick = nickInput.val();
        $.ajax({
            url: `/member/check/nick/${nick}`,
            type: 'get',
            statusCode: {
                // 서버 응답 상태 코드에 따른 처리
                200: function() {
                    nickInput.siblings('.invalid-feedback').css('color', 'green').text("사용 가능한 닉네임입니다.");
                    nickInput.removeClass('is-invalid');
                    nickInput.addClass('is-valid');
                    formValidation.isNickOk = true;
                    console.log("Success: 200 OK");
                },
                409: function() {
                    nickInput.siblings('.invalid-feedback').css('color', 'red').text("이미 사용중인 닉네임입니다.");
                    nickInput.removeClass('is-valid');
                    nickInput.addClass('is-invalid');
                    formValidation.isNickOk = false;
                    console.log("중복입니다. 409");
                }
            },
            complete: function(xhr, status) {
                // 요청이 완료된 후 실행되는 함수
                console.log("Complete - Status Code:", xhr.status);
            }
        });

        if (nick === '') {
            nickInput.removeClass('is-invalid');
            nickInput.removeClass('is-valid');
            nickInput.siblings('.invalid-feedback').text('');
            formValidation.isNickOk = false;
        }
    });

    // 휴대폰 검사
    $('input[name=hp]').focusout(function () {
        const hpInput = $(this);
        const hp = hpInput.val();

        validateInput($(this), reHp, '유효하지 않은 휴대폰 번호입니다.', 'isHpOk');

        if (formValidation.isHpOk) {
            // 중복 확인 API 호출
            $.ajax({
                url: `/member/check/hp/${hp}`,
                type: 'get',
                statusCode: {
                    // 서버 응답 상태 코드에 따른 처리
                    200: function() {
                        hpInput.siblings('.invalid-feedback').css('color', 'green').text("사용 가능한 휴대폰번호입니다.");
                        hpInput.removeClass('is-invalid');
                        hpInput.addClass('is-valid');
                        formValidation.isHpOk = true;
                        console.log("Success: 200 OK");
                    },
                    409: function() {
                        hpInput.siblings('.invalid-feedback').css('color', 'red').text("이미 사용중인 휴대폰번호입니다.");
                        hpInput.removeClass('is-valid');
                        hpInput.addClass('is-invalid');
                        formValidation.isHpOk = false;
                        console.log("중복입니다. 409");
                    }
                },
                complete: function(xhr, status) {
                    // 요청이 완료된 후 실행되는 함수
                    console.log("Complete - Status Code:", xhr.status);
                }
            });
        }

        if (hp === '') {
            hpInput.removeClass('is-invalid');
            hpInput.removeClass('is-valid');
            hpInput.siblings('.invalid-feedback').text('');
            formValidation.isHpOk = false;
        }
    });

    // 이메일 검사
    $('input[name=email]').focusout(function () {
        const emailInput = $(this);
        const email = emailInput.val();

        validateInput(emailInput, reEmail, '유효하지 않은 이메일 주소입니다.', 'isEmailOk');

        if (formValidation.isEmailOk) {
            // 중복 확인 API 호출
            $.ajax({
                url: `/member/check/email/${email}`,
                type: 'get',
                statusCode: {
                    // 서버 응답 상태 코드에 따른 처리
                    200: function() {
                        emailInput.siblings('.invalid-feedback').css('color', 'green').text("사용 가능한 이메일입니다.");
                        emailInput.removeClass('is-invalid');
                        emailInput.addClass('is-valid');
                        formValidation.isEmailOk = true;

                        // 유효성 검사가 통과했을 때만 버튼을 활성화
                        $("#btnEmailAuth").removeAttr('disabled');


                        console.log("Success: 200 OK");
                    },
                    409: function() {
                        emailInput.siblings('.invalid-feedback').css('color', 'red').text("이미 사용중인 이메일입니다.");
                        emailInput.removeClass('is-valid');
                        emailInput.addClass('is-invalid');
                        formValidation.isEmailOk = false;

                        // 유효성 검사가 통과하지 않았을 때는 버튼을 비활성화
                        $("#btnEmailAuth").attr('disabled', 'disabled');

                        console.log("중복입니다. 409");
                    }
                },
                complete: function(xhr, status) {
                    // 요청이 완료된 후 실행되는 함수
                    console.log("Complete - Status Code:", xhr.status);
                }
            });
        }

        if (email === '') {
            emailInput.removeClass('is-invalid');
            emailInput.removeClass('is-valid');
            emailInput.siblings('.invalid-feedback').text('');
            formValidation.isEmailOk = false;

            // 유효성 검사가 통과하지 않았을 때는 버튼을 비활성화
            $("#btnEmailAuth").attr('disabled', 'disabled');
        }
    });

    // 최종 확인
    $('.registerForm').submit(function (e) {
        e.preventDefault(); // 폼 제출 이벤트 중지

        // true면 전송, false면 전송 취소
        if (!formValidation.isUidOk || !formValidation.isPassOk1 || !formValidation.isPassOk2 || !formValidation.isNameOk || !formValidation.isEmailOk || !formValidation.isHpOk || !formValidation.isNickOk) {

            console.log('isUidOk:', formValidation.isUidOk);
            console.log('isPassOk1:', formValidation.isPassOk1);
            console.log('isPassOk2:', formValidation.isPassOk2);
            console.log('isNameOk:', formValidation.isNameOk);
            console.log('isEmailOk:', formValidation.isEmailOk);
            console.log('isHpOk:', formValidation.isHpOk);
            console.log('isNickOk:', formValidation.isNickOk);

            alert('입력 정보를 확인 해주세요.');
        } else {
            // 폼 전송
            this.submit();
        }
    });
});