
 function displayResults(data) {
    const tableBody = document.getElementById('results-body');
    tableBody.innerHTML = '';

    data.forEach(client => {
        const row = document.createElement('tr');

        const cell = document.createElement('td');
        const addTransportBtn = document.createElement('button');
        addTransportBtn.textContent = 'добавить транспорт';
        addTransportBtn.classList.add('add-transport-btn');

        addTransportBtn.addEventListener('click', () => {
            openTransportModal(client.id); // Передаем ID клиента
        });

        cell.appendChild(addTransportBtn);
        row.appendChild(cell);

        if (client.name != null) {
            const clientName = document.createElement('td');
            clientName.textContent = client.name;
            row.appendChild(clientName);
        }

        if (client.lastName != null) {
            const clientLastName = document.createElement('td');
            clientLastName.textContent = client.lastName;
            row.appendChild(clientLastName);
        }

        if (client.nickName != null) {
            const clientNick = document.createElement('td');
            clientNick.textContent = client.nickName;
            row.appendChild(clientNick);
        }

        if (client.phone != null) {
            const phone = document.createElement('td');
            phone.textContent = client.phone;
            row.appendChild(phone);
        }

        if (client.email != null) {
            const email = document.createElement('td');
            email.textContent = client.email;
            row.appendChild(email);
        }

        tableBody.appendChild(row);

        client.transportList.forEach(transport => {
            const transportRow = document.createElement('tr');

            // Кнопка для удаления транспорта
            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = 'Удалить';
            deleteBtn.classList.add('delete-btn');
            deleteBtn.addEventListener('click', () => {
                confirmDelete(transport.id);
            });
            const deleteCell = document.createElement('td');
            deleteCell.appendChild(deleteBtn);
            transportRow.appendChild(deleteCell); // Добавляем ячейку с кнопкой удаления в начало строки

            if (transport.brandName != null) {
                const brand = document.createElement('td');
                brand.textContent = transport.brandName;
                transportRow.appendChild(brand);
            }

            if (transport.model != null) {
                const model = document.createElement('td');
                model.textContent = transport.model;
                transportRow.appendChild(model);
            }

            if (transport.vin != null) {
                const vin = document.createElement('td');
                vin.textContent = transport.vin;
                transportRow.appendChild(vin);
            }

            if (transport.gosNumber != null) {
                const gosNumber = document.createElement('td');
                gosNumber.textContent = transport.gosNumber;
                transportRow.appendChild(gosNumber);
            }

            if (transport.year != null) {
                const year = document.createElement('td');
                year.textContent = transport.year;
                transportRow.appendChild(year);
            }

            if (transport.addInform != null) {
                const addInf = document.createElement('td');
                addInf.textContent = transport.addInform;
                transportRow.appendChild(addInf);
            }

            tableBody.appendChild(transportRow);
        });
    });
}