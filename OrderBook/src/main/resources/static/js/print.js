
function printTable() {
    var printContents = document.getElementById("sortOrdersList").innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
}