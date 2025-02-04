async function listRequests() {
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
    const headers = ['Клиент', 'Запчасть', 'Артикул',
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
            order.clientName,
            order.partName,
            order.partArticle,
            order.partBrand,
            order.partDealer,
            order.status,
            order.createDate,
            order.lastUpdateStatusTime,
            order.createManager
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
    requestsList.appendChild(table);


}


/*
   fetch("http://192.168.1.135:9090/garanties")
       .then(response => {
           if (!response.ok) {
               throw new Error("Сетевая ошибка");
           }
           return response.json(); // Преобразуем ответ в JSON
       })
       .then(data => {
           const requestsTable = document.getElementById('requestsTable'); // Предполагаем, что у вас есть элемент для отображения

           // Убедитесь, что таблица существует
           if (requestsTable) {
               // Очищаем предыдущие данные
               requestsTable.innerHTML = '';

               // Создаем строку заголовка
               const headerRow = document.createElement('tr');
               const headers = ['Клиент', 'Запчасть', 'Бренд', 'Дилер', 'Статус', 'Дата создания', 'Последнее обновление', 'Менеджер'];

               headers.forEach(headerText => {
                   const th = document.createElement('th');
                   th.textContent = headerText;
                   headerRow.appendChild(th);
               });

               // Добавляем строку заголовка в таблицу
               requestsTable.appendChild(headerRow);

               // Перебираем полученные данные и создаем строки таблицы
               data.forEach(request => {
                   const row = document.createElement('tr');
                   row.innerHTML = `
                       <td>${request.clientName}</td>
                       <td>${request.partName}</td>
                       <td>${request.partBrand}</td>
                       <td>${request.partDealer}</td>
                       <td>${request.status}</td>
                       <td>${request.createDate}</td>
                       <td>${request.lastUpdateStatusTime}</td>
                       <td>${request.createManager}</td>
                   `;
                   requestsTable.appendChild(row);
               });
           }
       })
       .catch(error => {
           console.error("Ошибка при получении данных:", error);
           alert("Не удалось загрузить список рекламаций.");
       });
}*/