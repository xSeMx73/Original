
// Функция для получения и отображения последних заказов
async function fetchRecentOrders() {
    const response = await fetch('http://192.168.1.135:9090/orders');
    const orders = await response.json(); // Преобразование ответа в JSON
    displayOrders(orders); // Вызываем функцию для отображения заказов
    document.getElementById('filterInput').style.display = 'block';

    // Привязываем функцию фильтрации к событию ввода текста
    document.getElementById('filterInput').addEventListener('input', function() {
        const filterText = this.value.toLowerCase();
        // Фильтруем заказы и отображаем только подходящие
        const filteredOrders = orders.filter(order => {
            return (
                order.productName.toLowerCase().includes(filterText) ||
                order.article.toLowerCase().includes(filterText) ||
                order.info.toLowerCase().includes(filterText)
            );
        });
        displayOrders(filteredOrders); // Показываем отфильтрованные заказы
    });
}
// Связываем кнопку с функцией при загрузке страницы
document.getElementById('viewOrdersButton').addEventListener('click', fetchRecentOrders);