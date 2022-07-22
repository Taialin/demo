var clickedlinks = [];
document.querySelectorAll("a").forEach((a)=>{
    a.addEventListener("click",addtoarr);
});
document.querySelector("form").addEventListener("submit",fnsubmit);

function addtoarr(){
    clickedlinks.push(this.outerHTML);
}

function fnsubmit(){
    this.querySelector("input[type=hidden]").value= JSON.stringify(clickedlinks);
    console.log(JSON.parse(this.querySelector("input[type=hidden]").value)[0]);
    event.preventDefault();//return true;
}