<script>
    function activeOptionSelection(sName, currentValue) {

    currentValue=currentValue.replace(" ", "_").replace("1", "");

    console.log(currentValue);


    for (var i = 0; i < sName.options.length; i++) {
    if (sName.options[i].value === currentValue) {
    console.log(sName.options[i].value === currentValue);
    sName.selectedIndex=i;
    break;
}
}



};
    document.addEventListener("DOMContentLoaded", ()=>{
    const sName = document.querySelectorAll(".select_payment");
    sName.forEach(item => {
    activeOptionSelection(item,item.firstElementChild.value);
});

    const sName1 = document.querySelectorAll(".select_completion");
    sName1.forEach(item => {
    activeOptionSelection(item,item.firstElementChild.value);
});
});
</script>




