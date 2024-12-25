
 function displayResults(data) {
    const tableBody = document.getElementById('results-body');
    tableBody.innerHTML = '';
    data.forEach(client => {


     ///////// Создаем строку с заголовками колонок, если это первый клиент
        const headerRow = document.createElement('tr');

        // Создаем ячейки заголовков
        const headers = ['','Имя клиента', 'Фамилия клиента', 'Прозвище', 'Телефон', 'E-mail', 'Компания'];

        headers.forEach(headerText => {
            const th = document.createElement('th');
            th.textContent = headerText; // Устанавливаем текст заголовка
            headerRow.appendChild(th);
        });

        tableBody.appendChild(headerRow); // Добавляем строку с заголовками в тело таблицы


///////////////////

        const row = document.createElement('tr');

//////////////////
        // Кнопка для удаления клиента
        const deleteClientBtn = document.createElement('button');
        deleteClientBtn.textContent = 'Удалить клиента';
        deleteClientBtn.classList.add('delete-btn');

        deleteClientBtn.addEventListener('click', () => {
            confirmDeleteClient(client.id); // Передаем ID клиента для удаления
        });

        const deleteClientCell = document.createElement('td');
        deleteClientCell.appendChild(deleteClientBtn);
        row.appendChild(deleteClientCell); // Добавляем ячейку с кнопкой удаления клиента

/////////////////

        // Кнопка изменения клиента
        const updateClientBtn = document.createElement("button");
        updateClientBtn.textContent = 'Редактировать клиента';
        updateClientBtn.classList.add('update-btn');

        updateClientBtn.addEventListener('click', () => {
            updateClient(client);
        });

        deleteClientCell.appendChild(updateClientBtn);



//////////////////


            const clientName = document.createElement('td');
            clientName.textContent = client.name;
            row.appendChild(clientName);



            const clientLastName = document.createElement('td');
            clientLastName.textContent = client.lastName;
            row.appendChild(clientLastName);



            const clientNick = document.createElement('td');
            clientNick.textContent = client.nickName;
            row.appendChild(clientNick);



            const phone = document.createElement('td');
            phone.textContent = client.phone;
            row.appendChild(phone);



            const email = document.createElement('td');
            email.textContent = client.email;
            row.appendChild(email);

            const company = document.createElement('td');
            company.textContent = client.company;
            row.appendChild(company);


        tableBody.appendChild(row);


 ///////////// Строка с заголовками для транспорта

        const headerTransportRow = document.createElement('tr');

        // Создаем ячейки заголовков
        const headersTransport = ['','Марка', 'Модель', 'VIN', 'Гос.номер', 'Год', 'Доп.информация'];

        headersTransport.forEach(headerText => {
            const th = document.createElement('th');
            th.textContent = headerText; // Устанавливаем текст заголовка
            headerTransportRow.appendChild(th);
        });

        tableBody.appendChild(headerTransportRow); // Добавляем строку с заголовками в тело таблицы




//////////


        client.transportList.forEach(transport => {
            const transportRow = document.createElement('tr');

            // Кнопка для удаления транспорта
            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = 'Удалить';
            deleteBtn.classList.add('delete-btn');
            deleteBtn.addEventListener('click', () => {
                confirmDelete(transport.id, client.id);
            });
            const deleteCell = document.createElement('td');
            deleteCell.appendChild(deleteBtn);
            transportRow.appendChild(deleteCell); // Добавляем ячейку с кнопкой удаления в начало строки

/////////////////

            // Кнопка изменения транспорта
            const updateTransportBtn = document.createElement("button");
            updateTransportBtn.textContent = 'Редактировать';
            updateTransportBtn.classList.add('update-btn');
            const clientID = client.id;
            updateTransportBtn.addEventListener('click', () => {
                updateTransport(transport, clientID);
            });

            deleteCell.appendChild(updateTransportBtn);


//////////////////


                const brand = document.createElement('td');
                brand.textContent = transport.brandName;
                transportRow.appendChild(brand);



                const model = document.createElement('td');
                model.textContent = transport.model;
                transportRow.appendChild(model);



                const vin = document.createElement('td');
                vin.textContent = transport.vin;
                transportRow.appendChild(vin);



                const gosNumber = document.createElement('td');
                gosNumber.textContent = transport.gosNumber;
                transportRow.appendChild(gosNumber);



                const year = document.createElement('td');
                year.textContent = transport.year;
                transportRow.appendChild(year);



                const addInf = document.createElement('td');
                addInf.textContent = transport.addInform;
                transportRow.appendChild(addInf);


            tableBody.appendChild(transportRow);
        });

        // Кнопка добавления транспорта

        const addTransportRow = document.createElement('tr');
        const addTransportCell = document.createElement('td');
        addTransportCell.colSpan = 1; // сколько количество колонок
        const addTransportBtn = document.createElement('button');
        addTransportBtn.textContent = 'Добавить транспорт';
        addTransportBtn.classList.add('add-transport-btn');

        addTransportBtn.addEventListener('click', () => {
            openTransportModal(client.id);
        });

        addTransportCell.appendChild(addTransportBtn);
        addTransportRow.appendChild(addTransportCell);
        tableBody.appendChild(addTransportRow);

    });


}