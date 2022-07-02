# snake-game

First project — contributing code to modify an already developed program: snake game.

Already developed aspects include:
- An apple and a moveable snake are interactable, set on a 2D grid
- Arrow keys move the snake (LEFT, RIGHT, UP, DOWN)
- When the snakes head collides into the apple, a displayed scoreboard has its score incremented
- Apple then randomly gets placed again and snake length increases by one unit
- Snake colour is fixed to green, and apple to red
- Upon collision or moving the snake into the borders of the program frame, a game over screen appears stating your score

IMPLEMENTATION PLANS:
- Fix existing bug: Snake self-collides after pressing two buttons simultaneously given a direction and length
- Plan to add a option to restart/replay the game
- Improve the playability
  - Include/add apples with varying effects
  - 2 Player option
  - More obstacles
- Add a title screen and several game modes

UPDATE 1
- Additional and unique green apple that doubles your total snake length and score
- Modified the green apple to randomly appear/disappear upon consuming a red apple

UPDATE 2
- Added a black apple that reduces the snakes length by 5. If this results in the length being <= 0, it's Game Over!
