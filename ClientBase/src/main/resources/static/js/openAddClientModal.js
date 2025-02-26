// Функция для открытия модального окна для добавления клиента
function openAddClientModal() {
    document.getElementById('add-client-modal').style.display = 'block';
}

function closeAddClientModal() {
    document.getElementById('add-client-modal').style.display = 'none';
}

function submitClient() {
    const name = document.getElementById('client-name').value;
    const lastName = document.getElementById('client-lastname').value;
    const nickName = document.getElementById('client-nickname').value;
    const phone = document.getElementById('client-phone').value;
    const email = document.getElementById('client-email').value;
    const company = document.getElementById('client-company').value;

    const clientData = {
        name,
        lastName,
        nickName,
        phone,
        email,
        company
    };

    fetch('http://192.168.1.135:9090/clients', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(clientData),
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
                closeAddClientModal();
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