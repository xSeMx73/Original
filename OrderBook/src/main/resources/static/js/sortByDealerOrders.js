async function fetchSortByDealerOrdersForClients() {
    const response = await fetch('http://192.168.1.135:9090/orders/sortByDealerOrders');
    const sortOrders = await response.json(); // Преобразование ответа в JSON
    displaySortOrders(sortOrders); // Вызываем функцию для отображения заказов
}

function displaySortOrders(sortOrders) {
    const sortOrdersList = document.getElementById('sortOrdersList');
    const ordersList = document.getElementById('ordersList');
    sortOrdersList.innerHTML = ''; // Очистить предыдущие содержимое
    ordersList.innerHTML = ''; // Очистить предыдущие содержимое



    // Генерация HTML для списка заказов в виде таблицы
    const table = document.createElement('table');
    table.border = '1'; // Добавляет границы к таблице
    table.style.width = '100%'; // Устанавливает ширину таблицы

    // Создаем заголовок таблицы
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const headers = ['Артикул', 'Наименование', 'Бренд', 'Количество',
       'Цена продажи', 'Менеджер', 'Доп.информация', 'День доставки'];

    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Создаем тело таблицы
    const tbody = document.createElement('tbody');

    let dealerTemp = ''; // Инициализируем переменную для хранения предыдущего дилера.
    let shortDealer = '';

    sortOrders.forEach(sortOrder => {
        const row = document.createElement('tr');
        shortDealer = sortOrder.dealer.match(/^\S+/)[0]; // Извлекаем первое слово

        // Проверяем, изменился ли дилер
        if (dealerTemp !== shortDealer) {
            dealerTemp = shortDealer; // Обновляем dealerTemp на текущее первое слово дилера

            // Создаем новую строку для дилера
            const dealerRow = document.createElement('tr');
            const dealerCell = document.createElement('td');
            dealerCell.colSpan = headers.length; // Ячейка занимает всю ширину таблицы
            dealerCell.innerHTML = `<span class="supplier-title">${dealerTemp}</span>`;
            dealerRow.appendChild(dealerCell);
            tbody.appendChild(dealerRow); // Добавляем строку дилера в тело таблицы
        }

        const cells = [
            sortOrder.article,
            sortOrder.productName,
            sortOrder.brand,
            sortOrder.quantity,
            sortOrder.price,
            sortOrder.manager,
            sortOrder.info,
            formatDate2(sortOrder.deliveryTime)
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
    sortOrdersList.appendChild(table);
    document.getElementById("printButton").style.display = "block"; // Показываем кнопку
}

// Связываем кнопку с функцией при загрузке страницы
document.getElementById('sortByDealerOrdersButton').addEventListener('click', fetchSortByDealerOrdersForClients);