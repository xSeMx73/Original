// Функция для подтверждения удаления
let transportToDeleteId;
let clientToUpdateId;

function confirmDelete(transportId, clientId) {
    transportToDeleteId = transportId; // Сохраняем ID удаляемого транспорта
    clientToUpdateId = clientId;
    document.getElementById('confirm-modal').style.display = 'block';
}

function cancelDelete() {
    transportToDeleteId = null;
    document.getElementById('confirm-modal').style.display = 'none';
}

async function executeDelete() {
    if (transportToDeleteId) {
        await fetch(`http://192.168.1.201:9090/transport/${transportToDeleteId}`, {
            method: 'DELETE',
        });
        transportToDeleteId = null;
        document.getElementById('confirm-modal').style.display = 'none';
        returnClientAfterCreate(clientToUpdateId); // Обновляем список клиентов после удаления
    }
}
