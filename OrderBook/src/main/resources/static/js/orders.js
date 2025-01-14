

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

// Функция для отображения заказов в виде таблицы
function displayOrders(orders) {
    const ordersList = document.getElementById('ordersList');
    ordersList.innerHTML = ''; // Очистить предыдущие содержимое

    // Генерация HTML для списка заказов в виде таблицы
    const table = document.createElement('table');
    table.border = '1'; // Добавляет границы к таблице
    table.style.width = '100%'; // Устанавливает ширину таблицы

    // Создаем заголовок таблицы
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const headers = ['Артикул', 'Наименование', 'Бренд', 'Поставщик', 'Количество',
        'Цена', 'Менеджер', 'Доп.информация', 'Время заказа', 'День доставки'];

    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Создаем тело таблицы
    const tbody = document.createElement('tbody');

    orders.forEach(order => {
        const row = document.createElement('tr');

        const cells = [
            order.article,
            order.productName,
            order.brand,
            order.dealer,
            order.quantity,
            order.price,
            order.manager,
            order.info,
            formatDate(order.createTime),
            formatDate2(order.deliveryTime)
        ];

        cells.forEach(cellText => {
            const td = document.createElement('td');
            td.textContent = cellText;
            row.appendChild(td);
        });

        tbody.appendChild(row);
    });
    table.appendChild(tbody);

    // Добавляем таблицу в контейнер
    ordersList.appendChild(table);
}

// Связываем кнопку с функцией при загрузке страницы
document.getElementById('viewOrdersButton').addEventListener('click', fetchRecentOrders);