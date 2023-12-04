# java-text-menu v2.1
A highly advanced, very cool and professionally coded text-based menu predominantly aimed at choosing between enums via a controller.
<p>
<i>By valsei!!</i> [https://github.com/valsei/java-text-menu]
<p>
Intended usage is to remove the clutter of 8+ different autonomous programs to choose between. Now just choose one!

## Setting up a menu
Start by creating a menu and input object, which is used in bridging controller input to the menu.
```java
TextMenu menu = new TextMenu();
MenuInput menuInput = new MenuInput(MenuInput.InputType.CONTROLLER);
```
Determine what setup for options will be necessary.
```java
enum Colors {
    RED,
    BLUE,
    YELLOW,
    GREEN,
}
double minWaitTime = 5.0;
double maxWaitTime = 15.0;
```
Add corresponding menu elements to the menu. Note that hoverable (interactable) menu elements must have an identifier name for retrieving results later.
```java
menu.add(new MenuHeader("Example text!"))
    .add("Menu header shortcut!?")
    .add() // empty line for spacing
    .add("Pick a color:")
    .add("color-picker-1", Colors.class) // enum selector shortcut
    .add()
    .add("Wait time:")
    .add("wait-slider-1", new MenuSlider(minWaitTime, maxWaitTime))
    .add()
    .add("finish-button-1", new MenuFinishedButton())
;
```
## Interacting with a menu
Once done with creating a menu and menu input object, there should be a loop that passes input to the menu until it is complete. The method `menu.isCompleted()` is an easy way to know when to stop, but this may end prematurely. If some elements always have a value (ex. slider) and are therefore always completed, even if they haven't been reached yet the menu will count as completed. This can be dealt with by either having the end loop logic bound to both the completed state and an extra input (ex. B button), or by adding a `MenuFinishedButton` to the end of the menu.
<p>
Example code:

```java
while (!menu.isCompleted()) {
    // get x,y (stick) and select (A) input from controller
    menuInput.update(x, y, select);
    menu.updateWithInput(menuInput);
    // display the updated menu
    for (String line : menu.toListOfStrings() {
        print(line); // but with appropriate printing method
    }
}
```
## Reading from a menu
Example code:
```java
// the second parameter is the type to return as
Colors chosenColor = menu.getResult("color-picker-1", Colors.class);
double waitTime = menu.getResult("wait-slider-1", Double.class);
```
```java
switch (menu.getResult("color-picker-1", Colors.class)) {
    case RED:
    // etc.
}
```