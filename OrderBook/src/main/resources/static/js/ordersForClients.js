
async function fetchOrdersForClients() {
    const response = await fetch('http://192.168.1.135:9090/orders/clientsOrders');
    const clientsOrders = await response.json(); // Преобразование ответа в JSON
    displayOrders(clientsOrders); // Вызываем функцию для отображения заказов
    document.getElementById('filterInput').style.display = 'block';

    // Привязываем функцию фильтрации к событию ввода текста
    document.getElementById('filterInput').addEventListener('input', function() {
        const filterText = this.value.toLowerCase();
        // Фильтруем заказы и отображаем только подходящие
        const filteredClientsOrders = clientsOrders.filter(order2 => {
            return (
                order2.productName.toLowerCase().includes(filterText) ||
                order2.article.toLowerCase().includes(filterText) ||
                order2.info.toLowerCase().includes(filterText)
            );
        });
        displayOrders(filteredClientsOrders); // Показываем отфильтрованные заказы
    });
}



// Связываем кнопку с функцией при загрузке страницы
document.getElementById('viewClientsOrdersButton').addEventListener('click', fetchOrdersForClients);