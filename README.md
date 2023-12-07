# ChestCreator
Tool for easy creation of chests in Json file for Nukkit

> [!IMPORTANT]
> This plugin is dependent on the [FakeInventories](https://github.com/IWareQ/FakeInventories) library

## How to use

> All chests are stored in the plugins/ChestCreator/chests folder

Example chest:

`example.json`
```json5
{
    // Command
    "command": {
        // Is enable
        "enable": true,
        "name": "example",
        "description": "Example command", 
        // Command aliases
        "aliases": ["example1"],
        // Permission
        // Delete it if you don't have to
        "permission": "chestcreator.example"
    },
    // Chest title
    "title": "Example chest",
    // Inventory type
    // Available: CHEST, DOUBLE_CHEST, HOPPER, DROPPER
    "type": "CHEST",
    // Chest items
    "items": {
        "12": { // <-- Item slot
            // Item id (Minecraft item id)
            "id": "minecraft:iron_sword",
            "name": "First item",
            // Item lore
            "lore": [
                "&dHello, %player%",
                "&6Click me!"
            ],
            // Item actions
            "actions": [
                {
                    // Available types: 
                    // PLAYER_COMMAND - Execute command from player
                    // CONSOLE_COMMAND  - Execute command from server
                    // MESSAGE - Send message to player
                    // PLAYER_MESSAGE - Send message from player
                    // OPEN - Open other chest
                    // OPEN_FORM - Open form from FormCreator
                    "type": "OPEN",    // Type
                    "value": "example" // Action value
                }
            ]   
        },
        "14": {
            "id": "minecraft:barrier",
            "name": "Close",
            "canTake": false, // Can a player take an item
            "close": true, // Close the chest after a click
            "lore": ["&cClick to close"],
            "actions": [
                {
                    "type": "PLAYER_COMMAND",
                    "value": "say Test"
                }
            ]
        }
    },
    // Chest open actions
    "openActions": [
        {
            "type": "CONSOLE_COMMAND",
            "value": "say %player% Chest opened!"
        }
    ],
    // Chest close actions
    "closeActions": [
        {
            "type": "CONSOLE_COMMAND",
            "value": "say %player% Chest closed!"
        }
    ]
}
```
Registration of the chest:

`chests.yml`
```yml
# Registration of the chest
chests:
  # chest name: "chest path in the 'chests' folder"
  example: "example.json"
```

## Commands
| Name          | Sub Command | Usage                               | Description       | Permission        |
|---------------|-------------|-------------------------------------|-------------------|-------------------|
| /chestcreator | open        | /chestcreator open <chest> <player> | Open player chest | chestcreator.open |
| /chestcreator | info        | /chestcreator info                  | Plugin info       | chestcreator.info |
| /chestcreator | help        | /chestcreator help                  | Help              |                   |

## API
### ChestManager
Get a folder with chests:
```java
File folder = ChestManager.getChestsFolder();
```
Getting a chest by name:
```java
Chest chest = ChestManager.get("chest_name");
```
Check the existence of the chest:
```java
ChestManager.exists("chest_name");
```
Load chest from file:
```java
ChestManager.load("chest_name", file);
```

### Creating a custom action executor

Your executor:
```java
public class YourExecutor implements Executor {

    /**
     * @param player Player
     * @param value  Value of action
     */
    @Override
    public void execute(Player player, String value) {
        // Handling action
    }
}
```

Executor registration:
```java
// name - Executor name. For example: YOUR_EXECUTOR 
ExecutorManager.register("name", new YourExecutor());
```
Getting a executor:
```java
ExecutorManager.getExecutor("YOUR_EXECUTOR");
```

### Events
| Name           | Cancellable | Description                      |
|----------------|-------------|----------------------------------|
| ChestLoadEvent | true        | Called when the chest is loaded  |
| ChestSendEvent | true        | Called when a chest is opened    |

# 

> [!TIP]
> Chest slots identifiers
> 
> ![image](https://github.com/MEFRREEX/ChestCreator/assets/83061703/95e27994-0668-4654-b41c-d846148550f4)

