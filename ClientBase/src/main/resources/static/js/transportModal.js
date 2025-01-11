function openTransportModal(clientId) {
    document.getElementById('modal').style.display = 'block';
    document.getElementById('client-id').value = clientId; // Сохраняем ID клиента
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

function submitTransport() {
    const clientId = document.getElementById('client-id').value;
    const brandName = document.getElementById('brand-name').value;
    const model = document.getElementById('model').value;
    const vin = document.getElementById('vin').value;
    const gosNumber = document.getElementById('gos-number').value;
    const year = document.getElementById('year').value;
    const addInform = document.getElementById('additional-info').value;

    const transportData = {
        brandName,
        model,
        vin,
        gosNumber,
        year,
        addInform
    };

    fetch('http://192.168.1.135:9090/transport', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'id': clientId
        },
        body: JSON.stringify(transportData),
    })
        .then(response => response.json())
        .then(data => {
            closeModal();
            returnClientAfterCreate(clientId); // Обновляем список клиентов после добавления
        })
        .catch((error) => console.error('Ошибка:', error));
}