



function updateClient(client) {
    // Заполнение полей формы данными клиента
    document.getElementById('client-name-update').value = client.name || '';
    document.getElementById('client-lastname-update').value = client.lastName || '';
    document.getElementById('client-nickname-update').value = client.nickName || '';
    document.getElementById('client-phone-update').value = client.phone || '';
    document.getElementById('client-email-update').value = client.email || '';
    document.getElementById('client-company-update').value = client.company || '';

    // Отображение модального окна
    const modal = document.getElementById('edit-client-modal');
    modal.style.display = 'block';

    // Закрытие модального окна при нажатии на кнопку закрытия
    const closeButton = document.querySelector('.close-button');
    closeButton.onclick = function() {
        modal.style.display = 'none';
    };

    // Закрытие модального окна при нажатии вне его
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    };

    // Обработка отправки формы
    const form = document.getElementById('edit-client-form');
    form.onsubmit = function(event) {
        event.preventDefault();
        // Сохраните изменения клиента
        const updatedClient = {
            id: client.id,
            name: document.getElementById('client-name-update').value,
            lastName: document.getElementById('client-lastname-update').value,
            nickName: document.getElementById('client-nickname-update').value,
            phone: document.getElementById('client-phone-update').value,
            email: document.getElementById('client-email-update').value,
            company: document.getElementById('client-company-update').value,
        };

        saveClientChanges(updatedClient);
        modal.style.display = 'none'; // Закрыть модальное окно
    };
}

function saveClientChanges(updatedClient) {


    fetch('http://192.168.1.201:9090/clients', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedClient),
    })
        .then(response => {
            // Проверка, что ответ успешен (статус 200-299)
            if (!response.ok) {
                throw new Error('Некорректно заполнены поля');

            }
            return response.json(); // Возврат JSON-ответа
        })
        .then(data => {
            if (data.id) { // Проверяем, что ID клиента действительно присутствует
                const clientId = data.id; // Сохраняем ID клиента
                returnClientAfterCreate(clientId); // Передаем ID в функцию
            } else {
                console.error('ID клиента не найден в ответе:', data);
            }
        })
        .catch((error) => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка: ' + error.message); // Выводим ошибку
        });
}