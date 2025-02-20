
async function listRequests() {
    closeModalEdit()
    closeModal()
    const reqList = await fetch('http://192.168.1.135:9090/garanties');
    const reqListJson = await reqList.json();
    const requestsList = document.getElementById('requestsTable');
    requestsList.innerHTML = ''; // Очистить предыдущие содержимое

    // Генерация HTML для списка заказов в виде таблицы
    const table = document.createElement('table');
    table.border = '1'; // Добавляет границы к таблице
    table.style.width = '100%'; // Устанавливает ширину таблицы

    // Создаем заголовок таблицы
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const headers = ['ID', 'Клиент', 'Запчасть', 'Артикул',
        'Бренд', 'Дилер', 'Статус', 'Дата создания', 'Последнее обновление', 'Менеджер'];

    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Создаем тело таблицы
    const tbody = document.createElement('tbody');

    reqListJson.forEach(order => {
        const row = document.createElement('tr');

        const cells = [
            order.id,
            order.clientName,
            order.partName,
            order.partArticle,
            order.partBrand,
            order.partDealer,
            statusConverterToString(order.status),
            order.createDate,
            order.lastUpdateStatusTime,
            order.createManager
        ];

        cells.forEach((cellText, index) => {
            const td = document.createElement('td');
            if (index === 0) { // Если это колонка ID
                const idLink = document.createElement('a');
                idLink.textContent = cellText;
                idLink.href = '#';
                idLink.onclick = () => openEditRequestModal(order.id); // Открытие формы редактирования
                td.appendChild(idLink);
            } else {
                td.textContent = cellText;
            }
            row.appendChild(td);
        });

        tbody.appendChild(row);
    });
    table.appendChild(tbody);
    requestsList.appendChild(table);
}
