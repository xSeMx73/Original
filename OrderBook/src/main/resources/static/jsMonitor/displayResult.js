function displayResults(data) {
    // Элемент, в который будет вставляться таблица
    const container = document.getElementById('resultsContainer');
    const table = document.createElement('table');
    table.setAttribute('border', '1');

    // Создаем заголовок таблицы
    const headerRow = document.createElement('tr');
    const headers = ["Информация", "Название продукта", "Бренд", "Артикул", "Количество", "Цена", "Дата поступления", "Вернуть ДО", "Убрать"];
    headers.forEach(headerText => {
        const headerCell = document.createElement('th');
        headerCell.innerText = headerText;
        headerRow.appendChild(headerCell);
    });
    table.appendChild(headerRow);

    // Группируем данные по менеджерам
    const groupedData = groupByManager(data);

    // Заполняем таблицу данными
    for (const manager in groupedData) {
        const managerRow = document.createElement('tr');
        const managerCell = document.createElement('td');
        managerCell.colSpan = headers.length;
        managerCell.innerText = `Менеджер: ${manager}`;
        managerRow.appendChild(managerCell);
        table.appendChild(managerRow);

        groupedData[manager].forEach(item => {
            const row = document.createElement('tr');
            const values = [item.info, item.productName, item.brand, item.article, item.quantity, item.price, item.inputData, item.returnDate];
            values.forEach(value => {
                const cell = document.createElement('td');
                cell.innerText = value;
                row.appendChild(cell);
            });

            // Добавляем кнопки убирания
            const actionsCell = document.createElement('td');
            const buttons = ["Продано", "На склад", "Вернули"];
            buttons.forEach(buttonText => {
                const button = document.createElement('button');
                button.innerText = buttonText;
                button.onclick = function() { handleButtonClick(buttonText, item.id); };
                actionsCell.appendChild(button);
            });
            row.appendChild(actionsCell);

            table.appendChild(row);
        });
    }

    // Добавляем таблицу в контейнер
    container.appendChild(table);
}

function groupByManager(data) {
    return data.reduce((acc, item) => {
        if (!acc[item.manager]) {
            acc[item.manager] = [];
        }
        acc[item.manager].push(item);
        return acc;
    }, {});
}

function handleButtonClick(reasonS, idS) {
    // Формируем данные для POST-запроса
    if (reasonS === "Продано") {reasonS = "SOLD";}
    if (reasonS === "На склад") {reasonS = "WAREHOUSE";}
    if (reasonS === "Вернули") {reasonS = "RETURNED";}

    // Выполняем POST-запрос
    fetch('http://192.168.1.135:9090/monitor', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'reason': reasonS,
            'id': idS
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка сети: ' + response.status);
            }
            location.reload();
            return response.json();
        })
        .then(data => {
            console.log('Успех:', data);
            // Обновляем отображение или интерфейс при успешном выполнении
            location.reload(); // Обновляем страницу после успешного выполнения
        })
        .catch(error => {
            console.error('Ошибка:', error);
            // Можно добавить дополнительную обработку ошибок (например, уведомление пользователю)
        });
}