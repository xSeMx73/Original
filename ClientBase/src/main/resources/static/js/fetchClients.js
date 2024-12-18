


async function fetchClients() {

    const query = document.getElementById('name').value;
    const response = await fetch('http://192.168.1.201:9090/clients?query=' + encodeURIComponent(query));
    const data = await response.json();
    console.log(data);
    displayResults(data);
}