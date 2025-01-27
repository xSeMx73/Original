document.addEventListener("DOMContentLoaded", function() {
    const url = "http://192.168.1.135:9090/monitor";

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Сеть ответила с ошибкой: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
            document.getElementById('resultsContainer').innerText = 'Ошибка загрузки данных: ' + error.message;
        });
});