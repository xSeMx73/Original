

function updateTransport(transport, clientID) {


    document.getElementById('brand-name-update').value = transport.brandName || '';
    document.getElementById('model-update').value = transport.model || '';
    document.getElementById('VIN-update').value = transport.vin || '';
    document.getElementById('gos-number-update').value = transport.gosNumber || '';
    document.getElementById('year-update').value = transport.year || '';
    document.getElementById('info-update').value = transport.addInform || '';

    // Отображение модального окна
    const modalUpdateTransport = document.getElementById('edit-transport-modal');
    modalUpdateTransport.style.display = 'block';

    // Закрытие модального окна при нажатии на кнопку закрытия
    const closeButtonUpdateTransport = document.querySelector('.close-button');
    closeButtonUpdateTransport.onclick = function() {
        modalUpdateTransport.style.display = 'none';
    };

    // Закрытие модального окна при нажатии вне его
    window.onclick = function(event) {
        if (event.target == modalUpdateTransport) {
            modalUpdateTransport.style.display = 'none';
        }
    };

    // Обработка отправки формы
    const formUpdateTransport = document.getElementById('edit-transport-form');
    formUpdateTransport.onsubmit = function(eventUpdateTransport) {
        eventUpdateTransport.preventDefault();
        // Сохраните изменения клиента
        const updatedTransport = {
            id: transport.id,
            brandName: document.getElementById('brand-name-update').value,
            model: document.getElementById('model-update').value,
            vin: document.getElementById('VIN-update').value,
            gosNumber: document.getElementById('gos-number-update').value,
            year: document.getElementById('year-update').value,
            addInform: document.getElementById('info-update').value,
        };

        saveTransportChanges(updatedTransport, clientID);
        modalUpdateTransport.style.display = 'none'; // Закрыть модальное окно
    };
}

function saveTransportChanges(updatedTransport, clientID) {


    fetch('http://192.168.1.201:9090/transport', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTransport),
    })
        .then(response => {
            // Проверка, что ответ успешен (статус 200-299)
            if (!response.ok) {
                throw new Error('Некорректно заполнены поля');
            }
            return response.json(); // Возврат JSON-ответа
        })
        .then(data => {
            if (data.id) { // Проверяем, что ID транспорта действительно присутствует
                returnClientAfterCreate(clientID); // Передаем ID в функцию
            } else {
                console.error('ID транспорта не найден в ответе:', data);
            }
        })
        .catch((error) => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка: ' + error.message); // Выводим ошибку
        });
}