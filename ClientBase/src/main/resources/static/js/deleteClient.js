let clientToDeleteId;

function confirmDeleteClient(clientId) {
    clientToDeleteId = clientId;
    document.getElementById('confirm-modal-deleteClient').style.display = 'block';

}

function cancelDeleteClient() {
    clientToDeleteId = null;
    document.getElementById('confirm-modal-deleteClient').style.display = 'none';
}

async function deleteClient() {

    if(clientToDeleteId) {
        fetch(`http://192.168.1.135:9090/clients/${clientToDeleteId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Клиент успешно удален!');
                    document.getElementById('confirm-modal-deleteClient').style.display = 'none';
                    fetchClients(); // Вызов функции для перезагрузки списка клиентов
                } else {
                    alert('Ошибка при удалении клиента.');
                    document.getElementById('confirm-modal-deleteClient').style.display = 'none';
                }
            })
            .catch(error => {
                console.error('Ошибка:', error);
            });
    }
}