# java-text-menu
A highly advanced, very cool and professionally coded
text-based menu predominantly aimed at
choosing between enums via a controller.
<p>
By valsei!! [https://github.com/valsei/java-text-menu]
<p>
Intended usage is to remove the clutter of 8+ different
autonomous programs to choose between. Now just choose one!
<p>
How to use: Create an autonomous framework method that makes decisions
based on enums. Then, give those enums to this text menu
and use it at the beginning of a single autonomous class.
Once the menu is complete, run the framework method but replace
any enum calls with menu.getSelectionResult(yourEnum.class) calls.
<p>
Example code:

```java
public enum Colors {
    RED,
    BLUE,
    GREEN,
    YELLOW,
}
```
```java
MenuInput menuInput = new MenuInput();

TextMenu menu = new TextMenu();
menu.add("Example Menu!")
    .add() // empty spacing line
    .add("colors:")
    .add(Colors.class);
```
```java
while (!menu.isCompleted()) {
    // get input of x, y and select

    // .update() returns itself so you can update and use in one line
    menu.updateWithInput(menuInput.update(x, y, select));
}
```
```java
switch (menu.getSelectionResult(Colors.class)) {
    case RED:
    case BLUE:
    case GREEN:
    case YELLOW:
}
```
