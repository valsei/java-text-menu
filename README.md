# java-text-menu v1.0.0
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

Important methods:
```java
// adds a MenuElement to the end of the menu
myMenu.add(something);
// returns the resulting enum that was chosen in its selection element
myMenu.getSelectionResult(enumClass);
// returns true if all elements have chosen options
myMenu.isCompleted();
// returns an ArrayList<String> that should be printed or logged
myMenu.toListOfStrings();
```
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

TextMenu myMenu = new TextMenu();
myMenu.add("Example Menu!")
    .add() // empty spacing line
    .add("colors:")
    .add(Colors.class);
```
```java
// .update() returns itself so you can update and use in one line
myMenu.updateWithInput(menuInput.update(x, y, select));
```
```java
switch (myMenu.getSelectionResult(Colors.class)) {
    case RED:
    case BLUE:
    case GREEN:
    case YELLOW:
}
```
