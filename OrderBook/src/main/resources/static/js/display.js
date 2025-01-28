/*
let currentPage = 0; // Текущая страница
const pageSize = 10; // Количество заказов на странице

function loadOrders(page) {
    fetch(`http://192.168.1.135:9090/orders?page=${page}&size=${pageSize}`)
        .then(response => response.json())
        .then(data => {
            console.log(data.content)
            displayOrders(data.content); // Отображаем заказы
            setupPagination(data); // Настраиваем пагинацию
        })
        .catch(error => console.error('Ошибка:', error));
}
*/
// Функция для отображения заказов в виде таблицы
function displayOrders(orders) {
    const sortOrdersList = document.getElementById('sortOrdersList');
    const ordersList = document.getElementById('ordersList');
    sortOrdersList.innerHTML = ''; // Очистить предыдущие содержимое
    ordersList.innerHTML = ''; // Очистить предыдущие содержимое
    document.getElementById("printButton").style.display = "none";

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
/*
function setupPagination(data) {
    const paginationControls = document.getElementById('paginationControls');
    paginationControls.innerHTML = ''; // Очистить предыдущие элементы пагинации

    const totalPages = data.totalPages; // Общее количество страниц

    // Первая страница
    const firstButton = document.createElement('button');
    firstButton.textContent = 'Первая';
    firstButton.disabled = currentPage === 0; // Отключаем, если это первая страница
    firstButton.onclick = () => {
        currentPage = 0;
        loadOrders(currentPage);
    };
    paginationControls.appendChild(firstButton);

    // Предыдущая страница
    const prevButton = document.createElement('button');
    prevButton.textContent = 'Предыдущая';
    prevButton.disabled = currentPage === 0; // Отключаем, если это первая страница
    prevButton.onclick = () => {
        if (currentPage > 0) {
            currentPage--;
            loadOrders(currentPage);
        }
    };
    paginationControls.appendChild(prevButton);

    // Создаем страницы
    for (let i = 0; i < totalPages; i++) {
        const button = document.createElement('button');
        button.textContent = i + 1; // Нумерация страниц
        button.className = (i === currentPage) ? 'active' : ''; // Добавляем активный класс
        button.onclick = () => {
            currentPage = i; // Устанавливаем текущую страницу
            loadOrders(currentPage); // Загружаем заказы для новой страницы
        };
        paginationControls.appendChild(button);
    }

    // Следующая страница
    const nextButton = document.createElement('button');
    nextButton.textContent = 'Следующая';
    nextButton.disabled = currentPage === totalPages - 1; // Отключаем, если это последняя страница
    nextButton.onclick = () => {
        if (currentPage < totalPages - 1) {
            currentPage++;
            loadOrders(currentPage);
        }
    };
    paginationControls.appendChild(nextButton);

    // Последняя страница
    const lastButton = document.createElement('button');
    lastButton.textContent = 'Последняя';
    lastButton.disabled = currentPage === totalPages - 1; // Отключаем, если это последняя страница
    lastButton.onclick = () => {
        currentPage = totalPages - 1;
        loadOrders(currentPage);
    };
    paginationControls.appendChild(lastButton);
}

// Инициализация первой загрузки заказов
loadOrders(currentPage);*/