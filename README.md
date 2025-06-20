# Simulation
![renderSimulation](https://github.com/user-attachments/assets/2e84a600-4f28-4d92-ac0e-af9b06ecf7a7)


---
Simulation - пошаговая симуляция 2D мира, населённого травоядными 
и хищниками. Кроме существ, мир содержит ресурсы (траву), которыми 
питаются травоядные, и статичные объекты, с которыми нельзя 
взаимодействовать - они просто занимают место.<br/>2D мир 
представляет из себя матрицу NxM, каждое существо или объект 
занимают клетку целиком, нахождение в клетке нескольких 
объектов/существ - недопустимо.

---
### Описание игры
- Проект написан на версии java 17.0.11
- Размер игрового поля 12х12.
- Хищник стремится напасть и съесть травоядное;
- Травоядное стремится напасть и съесть траву;
- Если отсутствует определенное количество травоядных и травы, то они создаются;
- Каждое существо имеет параметры:<br/>
Травоядные имеют здоровье и скорость (колличество ячеек, 
которое можно пройти за одну итерацию игрового цикла). 
Хищники имеют здоровье, скорость и силу атаки (количество урона, 
которое хищник наносит травоядному при атаке)
- Одна итерация игрового цикла включает в себя рендер текущего состония
игрового поля, а также движения травоядных/хищников к еде.
Если травоядное/хищник находится на расстоянии одной клетки от еды, 
то они могут атаковать ее;
---
### Как играть: <br/><br/>![сommandsSimulation](https://github.com/user-attachments/assets/65ba8418-28e7-48ef-928d-f615ff2ab79b)


При запуске программы в консоль 
распечатыватся игровое меню с возможными командами;

start - если симуляция не была запущена ранее, то происходит запуск;<br/>
pause - поставить симуляцию на паузу 
(поток продолжает работать, но не выполняет логику);<br/>
resume - снять с паузы симуляцию;<br/>
stop - полностью остановить и завершить поток симуляции, сбросить состояние
игрового поля. После этого можно либо запустить новую симуляцию,
либо выйти из программы (команда [exit]);<br/>
exit - полностью остановить и завершить поток симуляции, выйти из программы.

---
### Инструкции
1. Клонируйте репозиторий git clone https://github.com/xcvqqz/TheRealSimulation
2. Скомпилируйте и запустите проект.
---
