package com.example.finalprojectshared;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    private Button leftButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea playerStatsText;
    @FXML
    private TextArea npcStatsText;
    @FXML
    private Button attackButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Button runButton;
    private PlayerCharacter playerCharacter = new PlayerCharacter(20, 0, (int) ((Math.random() * 6) + (Math.random() * 6) + (Math.random() * 6)), (int) ((Math.random() * 6) + (Math.random() * 6) + (Math.random() * 6)), (int) ((Math.random() * 6) + (Math.random() * 6) + (Math.random() * 6)));
    private Maze maze = new Maze(new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, {21, 22, 23, 24, 25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36, 37, 38, 39, 40}, {41, 42, 43, 44, 45, 46, 47, 48, 49, 50}, {51, 52, 53, 54, 55, 56, 57, 58, 59, 60}, {61, 62, 63, 64, 65, 66, 67, 68, 69, 70}, {71, 72, 73, 74, 75, 76, 77, 78, 79, 80}, {81, 82, 83, 84, 85, 86, 87, 88, 89, 90}, {91, 92, 93, 94, 95, 96, 97, 98, 99, 100}}, 5, 5);
    private Room room = new Room(56, false, false, 0);
    private NPC npc = new NPC(0, 0, false);
    @FXML
    private TextArea roomNumberTextBox;

    public void initialize() {
        textArea.setEditable(false);
        playerStatsText.setEditable(false);
        npcStatsText.setEditable(false);

        playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
    }

    @FXML
    public void buttonClicked(ActionEvent actionEvent) {

        if ((actionEvent.getSource() == upButton) && (maze.getRow() > 0)) {
            maze.setTraveledDirection("north");
            if (determineIfRoomIsOnBlockedList()) {
                upButton.setDisable(true);
                textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
            } else if ((!determineIfRoomIsOnExploredList()) && (!determineIfRoomIsOnBlockedList())) {
                room.determineIfBlocked();
                if (!room.isBlocked()) {
                    maze.setRow((maze.getRow()) - 1);
                    room.setRoomNumber(maze.getRoomNumber());
                    roomNumberTextBox.clear();
                    roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                    room.determineIfThereIsNPC();
                    room.determineAmountOfGoldInRoom();
                    if (room.isNpc()) {
                        textArea.appendText("You've encountered an NPC! You must now choose to either attack it or run away from it..." + "\n");
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(true);
                        sleepButton.setDisable(true);
                        attackButton.setDisable(false);
                        runButton.setDisable(false);
                        npc.setHitPointsForNPC((int) ((Math.random() * 5) + 1));
                        npc.setStrengthDexterityAndIntelligenceOfNPC((npc.getHitPointsForNPC()) * 2);
                        npcStatsText.clear();
                        npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                    } else {
                        textArea.appendText("There seems to be no NPC in this room, so you can now choose to either search the room for gold or sleep..." + "\n");
                        attackButton.setDisable(true);
                        runButton.setDisable(true);
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(false);
                        sleepButton.setDisable(false);
                    }
                } else {
                    upButton.setDisable(true);
                    textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
                    maze.setRow((maze.getRow()) - 1);
                    room.setBlockedRoomNumber(maze.getRoomNumber());
                    room.addBlockedRoomToList();
                    maze.setRow((maze.getRow()) + 1);
                }
            } else if (determineIfRoomIsOnExploredList()) {
                textArea.appendText("There is nothing new to be seen in this room, as you have already fully explored it..." + "\n");
                maze.setRow((maze.getRow()) - 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
            }
        } else if ((actionEvent.getSource() == upButton) && (maze.getRow() <= 0)) {
            upButton.setDisable(true);
            textArea.appendText("It appears that you are at the edge of the maze, as you have come face to face with a towering wall that you cannot climb over. Therefore, you must head in a different direction..." + "\n");
        } else if ((actionEvent.getSource() == downButton) && (maze.getRow() < 9)) {
            maze.setTraveledDirection("south");
            if (determineIfRoomIsOnBlockedList()) {
                downButton.setDisable(true);
                textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
            } else if ((!determineIfRoomIsOnExploredList()) && (!determineIfRoomIsOnBlockedList())) {
                room.determineIfBlocked();
                if (!room.isBlocked()) {
                    maze.setRow((maze.getRow()) + 1);
                    room.setRoomNumber(maze.getRoomNumber());
                    roomNumberTextBox.clear();
                    roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                    room.determineIfThereIsNPC();
                    room.determineAmountOfGoldInRoom();
                    if (room.isNpc()) {
                        textArea.appendText("You've encountered an NPC! You must now choose to either attack it or run away from it..." + "\n");
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(true);
                        sleepButton.setDisable(true);
                        attackButton.setDisable(false);
                        runButton.setDisable(false);
                        npc.setHitPointsForNPC((int) ((Math.random() * 5) + 1));
                        npc.setStrengthDexterityAndIntelligenceOfNPC((npc.getHitPointsForNPC()) * 2);
                        npcStatsText.clear();
                        npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                    } else {
                        textArea.appendText("There seems to be no NPC in this room, so you can now choose to either search the room for gold or sleep..." + "\n");
                        attackButton.setDisable(true);
                        runButton.setDisable(true);
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(false);
                        sleepButton.setDisable(false);
                    }
                } else {
                    downButton.setDisable(true);
                    textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
                    maze.setRow((maze.getRow()) + 1);
                    room.setBlockedRoomNumber(maze.getRoomNumber());
                    room.addBlockedRoomToList();
                    maze.setRow((maze.getRow()) - 1);
                }
            } else if (determineIfRoomIsOnExploredList()) {
                textArea.appendText("There is nothing new to be seen in this room, as you have already fully explored it..." + "\n");
                maze.setRow((maze.getRow()) + 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
            }
        } else if ((actionEvent.getSource() == downButton) && (maze.getRow() >= 9)) {
            downButton.setDisable(true);
            textArea.appendText("It appears that you are at the edge of the maze, as you have come face to face with a towering wall that you cannot climb over. Therefore, you must head in a different direction..." + "\n");
        } else if ((actionEvent.getSource() == leftButton) && (maze.getColumn() > 0)) {
            maze.setTraveledDirection("west");
            if (determineIfRoomIsOnBlockedList()) {
                leftButton.setDisable(true);
                textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
            } else if ((!determineIfRoomIsOnExploredList()) && (!determineIfRoomIsOnBlockedList())) {
                room.determineIfBlocked();
                if (!room.isBlocked()) {
                    maze.setColumn((maze.getColumn()) - 1);
                    room.setRoomNumber(maze.getRoomNumber());
                    roomNumberTextBox.clear();
                    roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                    room.determineIfThereIsNPC();
                    room.determineAmountOfGoldInRoom();
                    if (room.isNpc()) {
                        textArea.appendText("You've encountered an NPC! You must now choose to either attack it or run away from it..." + "\n");
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(true);
                        sleepButton.setDisable(true);
                        attackButton.setDisable(false);
                        runButton.setDisable(false);
                        npc.setHitPointsForNPC((int) ((Math.random() * 5) + 1));
                        npc.setStrengthDexterityAndIntelligenceOfNPC((npc.getHitPointsForNPC()) * 2);
                        npcStatsText.clear();
                        npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                    } else {
                        textArea.appendText("There seems to be no NPC in this room, so you can now choose to either search the room for gold or sleep..." + "\n");
                        attackButton.setDisable(true);
                        runButton.setDisable(true);
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(false);
                        sleepButton.setDisable(false);
                    }
                } else {
                    leftButton.setDisable(true);
                    textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
                    maze.setColumn((maze.getColumn()) - 1);
                    room.setBlockedRoomNumber(maze.getRoomNumber());
                    room.addBlockedRoomToList();
                    maze.setColumn((maze.getColumn()) + 1);
                }
            } else if (determineIfRoomIsOnExploredList()) {
                textArea.appendText("There is nothing new to be seen in this room, as you have already fully explored it..." + "\n");
                maze.setColumn((maze.getColumn()) - 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
            }
        } else if ((actionEvent.getSource() == leftButton) && (maze.getColumn() <= 0)) {
            leftButton.setDisable(true);
            textArea.appendText("It appears that you are at the edge of the maze, as you have come face to face with a towering wall that you cannot climb over. Therefore, you must head in a different direction..." + "\n");
        } else if ((actionEvent.getSource() == rightButton) && (maze.getColumn() < 9)) {
            maze.setTraveledDirection("east");
            if (determineIfRoomIsOnBlockedList()) {
                rightButton.setDisable(true);
                textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
            } else if ((!determineIfRoomIsOnExploredList()) && (!determineIfRoomIsOnBlockedList())) {
                room.determineIfBlocked();
                if (!room.isBlocked()) {
                    maze.setColumn((maze.getColumn()) + 1);
                    room.setRoomNumber(maze.getRoomNumber());
                    roomNumberTextBox.clear();
                    roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                    room.determineIfThereIsNPC();
                    room.determineAmountOfGoldInRoom();
                    if (room.isNpc()) {
                        textArea.appendText("You've encountered an NPC! You must now choose to either attack it or run away from it..." + "\n");
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(true);
                        sleepButton.setDisable(true);
                        attackButton.setDisable(false);
                        runButton.setDisable(false);
                        npc.setHitPointsForNPC((int) ((Math.random() * 5) + 1));
                        npc.setStrengthDexterityAndIntelligenceOfNPC((npc.getHitPointsForNPC()) * 2);
                        npcStatsText.clear();
                        npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                    } else {
                        textArea.appendText("There seems to be no NPC in this room, so you can now choose to either search the room for gold or sleep..." + "\n");
                        attackButton.setDisable(true);
                        runButton.setDisable(true);
                        upButton.setDisable(true);
                        downButton.setDisable(true);
                        leftButton.setDisable(true);
                        rightButton.setDisable(true);
                        searchButton.setDisable(false);
                        sleepButton.setDisable(false);
                    }
                } else {
                    rightButton.setDisable(true);
                    textArea.appendText("This room is caved in, so you must choose to head in a different direction..." + "\n");
                    maze.setColumn((maze.getColumn()) + 1);
                    room.setBlockedRoomNumber(maze.getRoomNumber());
                    room.addBlockedRoomToList();
                    maze.setColumn((maze.getColumn()) - 1);
                }
            } else if (determineIfRoomIsOnExploredList()) {
                textArea.appendText("There is nothing new to be seen in this room, as you have already fully explored it..." + "\n");
                maze.setColumn((maze.getColumn()) + 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
            }
        } else if ((actionEvent.getSource() == rightButton) && (maze.getColumn() >= 9)) {
            rightButton.setDisable(true);
            textArea.appendText("It appears that you are at the edge of the maze, as you have come face to face with a towering wall that you cannot climb over. Therefore, you must head in a different direction..." + "\n");
        } else if (actionEvent.getSource() == attackButton) {
            textArea.appendText("You are going in for the attack!" + "\n");
            npc.setDieRollForPlayersAttack((int) ((Math.random() * 19) + 1));
            if (npc.getDieRollForPlayersAttack() >= npc.getStrengthDexterityAndIntelligenceOfNPC()) {
                npc.setPlayersHitAmount(playerCharacter.getStrength() / 3);
                npc.setHitPointsForNPC(npc.getHitPointsForNPC() - npc.getPlayersHitAmount());
                textArea.appendText("You have successfully managed to get a hit in on the NPC, and you dealt " + npc.getPlayersHitAmount() + " damage to the NPC!" + "\n");
                npcStatsText.clear();
                npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                if (npc.getHitPointsForNPC() > 0) {
                    npc.setDieRollForNPCAttack((int) ((Math.random() * 19) + 1));
                    if (npc.getDieRollForNPCAttack() >= playerCharacter.getDexterity()) {
                        npc.setNpcHitAmount(npc.getStrengthDexterityAndIntelligenceOfNPC() / 3);
                        playerCharacter.setHitPoints(playerCharacter.getHitPoints() - npc.getNpcHitAmount());
                        textArea.appendText("The NPC is still alive, and it successfully got a hit in on you... You have now taken " + npc.getNpcHitAmount() + " damage..." + "\n");
                        playerStatsText.clear();
                        playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
                    }
                }
            } else if (npc.getDieRollForPlayersAttack() < npc.getStrengthDexterityAndIntelligenceOfNPC()) {
                npc.setDieRollForNPCAttack((int) ((Math.random() * 19) + 1));
                if (npc.getDieRollForNPCAttack() >= playerCharacter.getDexterity()) {
                    npc.setNpcHitAmount(npc.getStrengthDexterityAndIntelligenceOfNPC() / 3);
                    playerCharacter.setHitPoints(playerCharacter.getHitPoints() - npc.getNpcHitAmount());
                    textArea.appendText("Unfortunately, you missed your attack... Furthermore, the NPC attacked you and dealt " + npc.getNpcHitAmount() + " damage to you..." + "\n");
                    playerStatsText.clear();
                    playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
                } else if (npc.getDieRollForNPCAttack() < playerCharacter.getDexterity()) {
                    textArea.appendText("You and the NPC stand in an awkward silence, as both of you did not manage to land a single hit on each other..." + "\n");
                }
            }
            if (npc.getHitPointsForNPC() <= 0) {
                textArea.appendText("You have defeated the NPC! You can now choose to either search the room for gold or sleep!" + "\n");
                attackButton.setDisable(true);
                runButton.setDisable(true);
                upButton.setDisable(true);
                downButton.setDisable(true);
                leftButton.setDisable(true);
                rightButton.setDisable(true);
                searchButton.setDisable(false);
                sleepButton.setDisable(false);
            }
            if (playerCharacter.getHitPoints() <= 0) {
                textArea.appendText("Your hit points have been depleted to zero, so you have died a grave death..." + "\n");
                attackButton.setDisable(true);
                runButton.setDisable(true);
                upButton.setDisable(true);
                downButton.setDisable(true);
                leftButton.setDisable(true);
                rightButton.setDisable(true);
                searchButton.setDisable(true);
                sleepButton.setDisable(true);
            }
        } else if (actionEvent.getSource() == runButton) {
            npc.setDieRollForRunAway((int) ((Math.random() * 19) + 1));
            if (npc.getDieRollForRunAway() < playerCharacter.getIntelligence()) {
                npc.setDieRollForNPCAttack((int) ((Math.random() * 19) + 1));
                if (npc.getDieRollForNPCAttack() >= playerCharacter.getDexterity()) {
                    npc.setNpcHitAmount(npc.getStrengthDexterityAndIntelligenceOfNPC() / 3);
                    playerCharacter.setHitPoints(playerCharacter.getHitPoints() - npc.getNpcHitAmount());
                    textArea.appendText("You have successfully ran away from the NPC! Though, on your way out the NPC spotted you, and it dealt " + npc.getNpcHitAmount() + " damage to you..." + "\n");
                    playerStatsText.clear();
                    playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
                } else if (npc.getDieRollForNPCAttack() < playerCharacter.getDexterity()) {
                    textArea.appendText("The NPC spotted you on your way out, but it fortunately did not have enough precision in its attack to hit you. Therefore, you successfully escaped the room without a scratch." + "\n");
                }
            } else if (npc.getDieRollForRunAway() >= playerCharacter.getIntelligence()) {
                textArea.appendText("You have successfully managed to escape the NPC without a scratch!" + "\n");
            }
            attackButton.setDisable(true);
            runButton.setDisable(true);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);
            upButton.setDisable(false);
            downButton.setDisable(false);
            leftButton.setDisable(false);
            rightButton.setDisable(false);
            if (maze.getTraveledDirection().equals("north")) {
                maze.setRow((maze.getRow()) + 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
            } else if (maze.getTraveledDirection().equals("south")) {
                maze.setRow((maze.getRow()) - 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
            } else if (maze.getTraveledDirection().equals("west")) {
                maze.setColumn((maze.getColumn()) + 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
            } else if (maze.getTraveledDirection().equals("east")) {
                maze.setColumn((maze.getColumn()) - 1);
                room.setRoomNumber(maze.getRoomNumber());
                roomNumberTextBox.clear();
                roomNumberTextBox.appendText("Room " + room.getRoomNumber() + "\n");
            }
            if (playerCharacter.getHitPoints() <= 0) {
                textArea.appendText("Your hit points have been depleted to zero, so you have died a grave death..." + "\n");
                attackButton.setDisable(true);
                runButton.setDisable(true);
                upButton.setDisable(true);
                downButton.setDisable(true);
                leftButton.setDisable(true);
                rightButton.setDisable(true);
                searchButton.setDisable(true);
                sleepButton.setDisable(true);
            }
        } else if (actionEvent.getSource() == searchButton) {
            room.setDieRollForGold((int) ((Math.random() * 19) + 1));
            if (room.getDieRollForGold() < playerCharacter.getIntelligence()) {
                playerCharacter.setGold(playerCharacter.getGold() + room.getGold());
                textArea.appendText("You searched the room and found " + room.getGold() + " gold!" + "\n");
                playerStatsText.clear();
                playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
            } else if (room.getDieRollForGold() >= playerCharacter.getIntelligence()) {
                textArea.appendText("You were unable to find any gold in this room..." + "\n");
            }
            searchButton.setDisable(true);
            sleepButton.setDisable(true);
            attackButton.setDisable(true);
            runButton.setDisable(true);
            upButton.setDisable(false);
            downButton.setDisable(false);
            leftButton.setDisable(false);
            rightButton.setDisable(false);
            room.setExploredRoomNumber(maze.getRoomNumber());
            room.addExploredRoomToList();
        } else if (actionEvent.getSource() == sleepButton) {
            playerCharacter.setHitPoints(20);
            playerStatsText.clear();
            playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
            room.determineIfNPCSpawnsWhileSleeping();
            if (room.isNpcSpawnsWhileSleeping()) {
                textArea.appendText("...While you were sleeping an NPC spotted you and attacked you! You must now choose to either attack it or run away from it..." + "\n");
                sleepButton.setDisable(true);
                searchButton.setDisable(true);
                upButton.setDisable(true);
                downButton.setDisable(true);
                leftButton.setDisable(true);
                rightButton.setDisable(true);
                attackButton.setDisable(false);
                runButton.setDisable(false);
                npc.setHitPointsForNPC((int) ((Math.random() * 5) + 1));
                npc.setStrengthDexterityAndIntelligenceOfNPC((npc.getHitPointsForNPC()) * 2);
                npcStatsText.clear();
                npcStatsText.appendText("Hit Points: " + npc.getHitPointsForNPC() + "\n" + "Strength: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Dexterity: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n" + "Intelligence: " + npc.getStrengthDexterityAndIntelligenceOfNPC() + "\n");
                npc.setNpcHitAmount(npc.getStrengthDexterityAndIntelligenceOfNPC() / 3);
                playerCharacter.setHitPoints(playerCharacter.getHitPoints() - npc.getNpcHitAmount());
                textArea.appendText("You took " + npc.getNpcHitAmount() + " damage from the NPC's attack while you were sleeping..." + "\n");
                playerStatsText.clear();
                playerStatsText.appendText("Hit Points: " + playerCharacter.getHitPoints() + "\n" + "Strength: " + playerCharacter.getStrength() + "\n" + "Dexterity: " + playerCharacter.getDexterity() + "\n" + "Intelligence: " + playerCharacter.getIntelligence() + "\n" + "Gold: " + playerCharacter.getGold() + "\n");
            } else {
                textArea.appendText("You have awoken from a pleasant slumber..." + "\n");
                sleepButton.setDisable(true);
                searchButton.setDisable(true);
                attackButton.setDisable(true);
                runButton.setDisable(true);
                upButton.setDisable(false);
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
                room.setExploredRoomNumber(maze.getRoomNumber());
                room.addExploredRoomToList();
            }
        }
    }

    public boolean determineIfRoomIsOnBlockedList() {
        if (maze.getTraveledDirection().equals("north")) {
            maze.setRow((maze.getRow()) - 1);
            room.setAttemptedRoomNumberToSeeIfBlocked(maze.getRoomNumber());
            maze.setRow((maze.getRow()) + 1);
            for (int i = 0; i < room.getBlockedRoomListSize(); i++) {
                room.setForLoopCounterForBlocked(i);
                room.setOnBlockedRoomsList(room.getAttemptedRoomNumberToSeeIfBlocked() == room.scanBlockedRoomsListForThisRoomNumber());
                if (room.isOnBlockedRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("south")) {
            maze.setRow((maze.getRow()) + 1);
            room.setAttemptedRoomNumberToSeeIfBlocked(maze.getRoomNumber());
            maze.setRow((maze.getRow()) - 1);
            for (int i = 0; i < room.getBlockedRoomListSize(); i++) {
                room.setForLoopCounterForBlocked(i);
                room.setOnBlockedRoomsList(room.getAttemptedRoomNumberToSeeIfBlocked() == room.scanBlockedRoomsListForThisRoomNumber());
                if (room.isOnBlockedRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("east")) {
            maze.setColumn((maze.getColumn()) + 1);
            room.setAttemptedRoomNumberToSeeIfBlocked(maze.getRoomNumber());
            maze.setColumn((maze.getColumn()) - 1);
            for (int i = 0; i < room.getBlockedRoomListSize(); i++) {
                room.setForLoopCounterForBlocked(i);
                room.setOnBlockedRoomsList(room.getAttemptedRoomNumberToSeeIfBlocked() == room.scanBlockedRoomsListForThisRoomNumber());
                if (room.isOnBlockedRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("west")) {
            maze.setColumn((maze.getColumn()) - 1);
            room.setAttemptedRoomNumberToSeeIfBlocked(maze.getRoomNumber());
            maze.setColumn((maze.getColumn()) + 1);
            for (int i = 0; i < room.getBlockedRoomListSize(); i++) {
                room.setForLoopCounterForBlocked(i);
                room.setOnBlockedRoomsList(room.getAttemptedRoomNumberToSeeIfBlocked() == room.scanBlockedRoomsListForThisRoomNumber());
                if (room.isOnBlockedRoomsList()) {
                    break;
                }
            }
        }
        return room.isOnBlockedRoomsList();
    }

    public boolean determineIfRoomIsOnExploredList() {
        if (maze.getTraveledDirection().equals("north")) {
            maze.setRow((maze.getRow()) - 1);
            room.setAttemptedRoomNumberToSeeIfExplored(maze.getRoomNumber());
            maze.setRow((maze.getRow()) + 1);
            for (int i = 0; i < room.getExploredRoomListSize(); i++) {
                room.setForLoopCounterForExplored(i);
                room.setOnExploredRoomsList(room.getAttemptedRoomNumberToSeeIfExplored() == room.scanExploredRoomsListForThisRoomNumber());
                if (room.isOnExploredRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("south")) {
            maze.setRow((maze.getRow()) + 1);
            room.setAttemptedRoomNumberToSeeIfExplored(maze.getRoomNumber());
            maze.setRow((maze.getRow()) - 1);
            for (int i = 0; i < room.getExploredRoomListSize(); i++) {
                room.setForLoopCounterForExplored(i);
                room.setOnExploredRoomsList(room.getAttemptedRoomNumberToSeeIfExplored() == room.scanExploredRoomsListForThisRoomNumber());
                if (room.isOnExploredRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("east")) {
            maze.setColumn((maze.getColumn()) + 1);
            room.setAttemptedRoomNumberToSeeIfExplored(maze.getRoomNumber());
            maze.setColumn((maze.getColumn()) - 1);
            for (int i = 0; i < room.getExploredRoomListSize(); i++) {
                room.setForLoopCounterForExplored(i);
                room.setOnExploredRoomsList(room.getAttemptedRoomNumberToSeeIfExplored() == room.scanExploredRoomsListForThisRoomNumber());
                if (room.isOnExploredRoomsList()) {
                    break;
                }
            }
        } else if (maze.getTraveledDirection().equals("west")) {
            maze.setColumn((maze.getColumn()) - 1);
            room.setAttemptedRoomNumberToSeeIfExplored(maze.getRoomNumber());
            maze.setColumn((maze.getColumn()) + 1);
            for (int i = 0; i < room.getExploredRoomListSize(); i++) {
                room.setForLoopCounterForExplored(i);
                room.setOnExploredRoomsList(room.getAttemptedRoomNumberToSeeIfExplored() == room.scanExploredRoomsListForThisRoomNumber());
                if (room.isOnExploredRoomsList()) {
                    break;
                }
            }
        }
        return room.isOnExploredRoomsList();
    }
}