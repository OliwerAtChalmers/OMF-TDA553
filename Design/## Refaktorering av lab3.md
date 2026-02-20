## Refaktorering av lab3
1. Skapa nya klasserna `VehicleState`. Lägg till konstruktorer och getters.  
2. Skapa klassen `SimulationEngine` med fält för `vehicles`, `worldWidth`, `worldHeight`. Flytta timer‑loopens rörelselogik från `VehicleController.TimerListener` till `SimulationEngine.tick()`.  
3. Flytta logiken för alla fordonskommandon från `VehicleController` till `SimulationEngine` (`gas`, `brake`, `turn`, `turbo`, `bed`). Låt `VehicleController` endast delega­ra anropen.  
4. Skapa `SimulationEngine.getVehicleStates()` som bygger en `State` av fordonens positionsdata.  
5. Ändra `VehicleView` så den har en metod `render(ArrayList<State>)` som skickar state till DrawPanel.  
6. Ändra `DrawPanel` så den bara renderar från `List<VehicleState>` och inte uppdaterar egna listor av positionsdata från controller.  
7. Skapa `SpriteLoader` som laddar bilder baserat på `modelName`. Låt `VehicleView` eller `DrawPanel` initiera sprites en gång vid uppstart.  
8. Koppla om `VehicleController.TimerListener` så den gör: `engine.tick()` och `view.render(engine.getVehicleStates())`.  
9. Rensa bort överflödiga fält/metoder som inte längre används (t.ex. listor i `DrawPanel` som speglar controller‑state). 
 
