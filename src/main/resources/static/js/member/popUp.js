//회원가입 이용약관 팝업
const body = document.body;
const dim = document.querySelector('.dim');
const popup = document.querySelector('.pop-box');
const termBoxLi = document.querySelector('.agree > ul > li:nth-child(1)');
const termBox = document.querySelector('#termsService');
const termEmailBox = document.querySelector('#termsEmail')
const btnPopClose = popup.querySelector('.pop-top button');
const btnPopApply = popup.querySelector('.popApply #policyBtn');
const popScroll = document.querySelector('.article-wrap');



function openTerm(){
    body.style.overflow = 'hidden';
    dim.style.display = 'block';
    popup.style.display = 'block';
    btnPopClose.focus();
    popScroll.scrollTop =0;
}
function closeTerm(){
    body.style.overflow = 'auto';
    dim.style.display = 'none';
    popup.style.display = 'none';
    termEmailBox.focus();
    popScroll.scrollTop =0;
}

termBox.addEventListener('click',()=>{
    if(termBoxLi.classList.contains('checked')){
        termBoxLi.classList.remove('checked');
    }else{
        termBoxLi.classList.add('checked');
        openTerm();
    }
});

btnPopClose.addEventListener('click',closeTerm);
btnPopApply.addEventListener('click',closeTerm);
dim.addEventListener('click',closeTerm);
