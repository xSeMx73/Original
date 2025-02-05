function openEditRequestModal(id) {

    closeModalEdit()
    const url = `http://192.168.1.135:9090/garanties/${id}`;

    const modal = document.createElement('div');
    modal.id = 'modal-edit';
    modal.className = 'modal-edit';
    modal.innerHTML = `

    <div class="modal-content">
        <span class="close" onClick="closeModalEdit()">&times;</span>
        <h2>Редактировать рекламацию</h2>
        <form id="editRequestForm">
            <label for="clientName">Клиент:</label>
            <input type="text" id="clientName" name="clientName" required/><br/><br/>
            <label for="clientPhone">Телефон клиента:</label>
            <input type="tel" id="clientPhone" name="clientPhone" required/><br/><br/>
            <label for="transportModel">Модель транспорта:</label>
            <input type="text" id="transportModel" name="transportModel" required/><br/><br/>
            <label for="transportBrand">Бренд транспорта:</label>
            <input type="text" id="transportBrand" name="transportBrand" required/><br/><br/>
            <label for="transportYear">Год транспорта:</label>
            <input type="number" id="transportYear" name="transportYear" required/><br/><br/>
            <label for="gosNumber">Госномер:</label>
            <input type="text" id="gosNumber" name="gosNumber" required/><br/><br/>
            <label for="mileageStart">Пробег на старте:</label>
            <input type="number" id="mileageStart" name="mileageStart" required/><br/><br/>
            <label for="mileageEnd">Пробег на окончании:</label>
            <input type="number" id="mileageEnd" name="mileageEnd" required/><br/><br/>
            <label for="vin">VIN:</label>
            <input type="text" id="vin" name="vin" required/><br/><br/>
            <label for="dateStartRepair">Дата начала ремонта:</label>
            <input type="date" id="dateStartRepair" name="dateStartRepair" required/><br/><br/>
            <label for="dateRemovePart">Дата снятия детали:</label>
            <input type="date" id="dateRemovePart" name="dateRemovePart"/><br/><br/>
            <label for="partArticle">Артикул детали:</label>
            <input type="text" id="partArticle" name="partArticle" required/><br/><br/>
            <label for="partBrand">Бренд детали:</label>
            <input type="text" id="partBrand" name="partBrand" required/><br/><br/>
            <label for="partName">Название детали:</label>
            <input type="text" id="partName" name="partName" required/><br/><br/>
            <label for="partDealer">Дилер:</label>
            <input type="text" id="partDealer" name="partDealer" required/><br/><br/>
            <label for="faultDescription">Описание неисправности:</label>
            <input type="text" id="faultDescription" name="faultDescription"/><br/><br/>
            <label for="createManager">Менеджер по созданию:</label>
            <input type="text" id="createManager" name="createManager" required/><br/><br/>
            <label for="status">Статус:</label>
            <select id="status" name="status" required>
                <option value="CREATED">Создана</option>
                <option value="SENDREQUESTDEALER">Заявка отправлена поставщику</option>
                <option value="SENDPARTDEALER">Запчасть отправлена поставщику</option>
                <option value="APPROVED">Одобрено</option>
                <option value="REJECTED">Отклонено</option>
                <option value="REQUESTMOREINFO">Запрос дополнительной информации поставщиком</option>
            </select><br/><br/>
            <button type="button" id="closeModalButton" onClick="closeModalEdit()">Закрыть</button>
            <button type="submit">Сохранить</button>
            <button type="button" onClick="printZakaz(id)">Заказ-наряд</button>
            <button type="button" onClick="printDefect(id)">Дефектовка</button>
        </form>
    </div>
`;

    document.body.appendChild(modal);
    modal.style.display = "block"; // Показываем модальное окно

    // Загружаем данные JSON по указанному адресу
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Сетевой ответ не OK');
            }
            return response.json();
        })
        .then(data => {
            // Заполняем поля формы данными
            document.getElementById('clientName').value = data.clientName || '';
            document.getElementById('clientPhone').value = data.clientPhone || '';
            document.getElementById('transportModel').value = data.transportModel || '';
            document.getElementById('transportBrand').value = data.transportBrand || '';
            document.getElementById('transportYear').value = data.transportYear || '';
            document.getElementById('gosNumber').value = data.gosNumber || '';
            document.getElementById('mileageStart').value = data.mileageStart || '';
            document.getElementById('mileageEnd').value = data.mileageEnd || '';
            document.getElementById('vin').value = data.vin || '';
            document.getElementById('dateStartRepair').value = data.dateStartRepair || '';
            document.getElementById('dateRemovePart').value = data.dateRemovePart || '';
            document.getElementById('partArticle').value = data.partArticle || '';
            document.getElementById('partBrand').value = data.partBrand || '';
            document.getElementById('partName').value = data.partName || '';
            document.getElementById('partDealer').value = data.partDealer || '';
            document.getElementById('faultDescription').value = data.faultDescription || '';
            document.getElementById('createManager').value = data.createManager || '';
            document.getElementById('status').value = data.status || '';

            // Показываем модальное окно
            document.getElementById('editRequestModal').style.display = 'block';
        })
        .catch(error => {
            console.error('Ошибка при получении данных:', error);
        });


// Обработка отправки формы
 const form = document.getElementById('editRequestForm');
  form.onsubmit = function(event) {
    event.preventDefault();
    // Сохраните изменения клиента
    const updatedRequest = {
        id: id,
        clientName: document.getElementById('clientName').value,
        clientPhone: document.getElementById('clientPhone').value,
        transportModel: document.getElementById('transportModel').value,
        transportBrand: document.getElementById('transportBrand').value,
        transportYear: document.getElementById('transportYear').value,
        gosNumber: document.getElementById('gosNumber').value,
        mileageStart: document.getElementById('mileageStart').value,
        mileageEnd: document.getElementById('mileageEnd').value,
        vin: document.getElementById('vin').value,
        dateStartRepair: document.getElementById('dateStartRepair').value,
        dateRemovePart: document.getElementById('dateRemovePart').value,
        partArticle: document.getElementById('partArticle').value,
        partBrand: document.getElementById('partBrand').value,
        partName: document.getElementById('partName').value,
        partDealer: document.getElementById('partDealer').value,
        faultDescription: document.getElementById('faultDescription').value,
        createManager: document.getElementById('createManager').value,
        status: document.getElementById('status').value,
    };

    saveEditRequest(updatedRequest);
    modal.style.display = 'none'; // Закрыть модальное окно

  };

}

// Функция для закрытия модального окна
function closeModalEdit() {
    const modal = document.getElementById('modal-edit');
    if (modal) {
        modal.style.display = "none"; // Скрываем модальное окно
        document.body.removeChild(modal); // Удаляем модальное окно из DOM
    }
}

function saveEditRequest(updatedRequest) {
    fetch('http://192.168.1.135:9090/garanties', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedRequest),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Некорректно заполнены поля');
            }
        })
        .then(data => {
            listRequests(); // Полная перезагрузка страницы
        })
        .catch((error) => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка: ' + error.message); // Выводим ошибку
        });
}

