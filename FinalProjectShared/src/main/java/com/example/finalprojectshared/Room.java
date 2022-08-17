package com.example.finalprojectshared;

import java.util.ArrayList;

public class Room {

    private int roomNumber;
    private int blockedRoomNumber;
    private int exploredRoomNumber;
    private int attemptedRoomNumberToSeeIfBlocked;
    private int attemptedRoomNumberToSeeIfExplored;
    private int forLoopCounterForBlocked;
    private int forLoopCounterForExplored;
    private boolean blocked;
    private boolean npc;
    private boolean isOnBlockedRoomsList;
    private boolean isOnExploredRoomsList;
    private boolean npcSpawnsWhileSleeping;
    private int gold;
    private int dieRollForGold;
    private ArrayList<Integer> blockedRooms = new ArrayList<Integer>();
    private ArrayList<Integer> exploredRooms = new ArrayList<Integer>();

    public Room(int roomNumber, boolean blocked, boolean npc, int gold) {
        this.roomNumber = roomNumber;
        this.blocked = blocked;
        this.npc = npc;
        this.gold = gold;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isNpc() {
        return npc;
    }

    public void setNpc(boolean npc) {
        this.npc = npc;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getBlockedRoomNumber() {
        return blockedRoomNumber;
    }

    public void setBlockedRoomNumber(int blockedRoomNumber) {
        this.blockedRoomNumber = blockedRoomNumber;
    }

    public boolean isNpcSpawnsWhileSleeping() {
        return npcSpawnsWhileSleeping;
    }

    public void setNpcSpawnsWhileSleeping(boolean npcSpawnsWhileSleeping) {
        this.npcSpawnsWhileSleeping = npcSpawnsWhileSleeping;
    }

    public int getAttemptedRoomNumberToSeeIfBlocked() {
        return attemptedRoomNumberToSeeIfBlocked;
    }

    public void setAttemptedRoomNumberToSeeIfBlocked(int attemptedRoomNumberToSeeIfBlocked) {
        this.attemptedRoomNumberToSeeIfBlocked = attemptedRoomNumberToSeeIfBlocked;
    }

    public int getForLoopCounterForBlocked() {
        return forLoopCounterForBlocked;
    }

    public void setForLoopCounterForBlocked(int forLoopCounterForBlocked) {
        this.forLoopCounterForBlocked = forLoopCounterForBlocked;
    }

    public boolean isOnBlockedRoomsList() {
        return isOnBlockedRoomsList;
    }

    public void setOnBlockedRoomsList(boolean onBlockedRoomsList) {
        isOnBlockedRoomsList = onBlockedRoomsList;
    }

    public int getExploredRoomNumber() {
        return exploredRoomNumber;
    }

    public void setExploredRoomNumber(int exploredRoomNumber) {
        this.exploredRoomNumber = exploredRoomNumber;
    }

    public int getAttemptedRoomNumberToSeeIfExplored() {
        return attemptedRoomNumberToSeeIfExplored;
    }

    public void setAttemptedRoomNumberToSeeIfExplored(int attemptedRoomNumberToSeeIfExplored) {
        this.attemptedRoomNumberToSeeIfExplored = attemptedRoomNumberToSeeIfExplored;
    }

    public int getForLoopCounterForExplored() {
        return forLoopCounterForExplored;
    }

    public void setForLoopCounterForExplored(int forLoopCounterForExplored) {
        this.forLoopCounterForExplored = forLoopCounterForExplored;
    }

    public boolean isOnExploredRoomsList() {
        return isOnExploredRoomsList;
    }

    public void setOnExploredRoomsList(boolean onExploredRoomsList) {
        isOnExploredRoomsList = onExploredRoomsList;
    }

    public void determineIfBlocked() {
        double randomNumberForBlocked = Math.random() * 10;
        if (randomNumberForBlocked < 1) {
            blocked = true;
        } else if (randomNumberForBlocked >= 1) {
            blocked = false;
        }
    }

    public void determineIfThereIsNPC() {
        double randomNumberForNPC = Math.random() * 100;
        if (randomNumberForNPC < 50) {
            npc = true;
        } else if (randomNumberForNPC >= 50) {
            npc = false;
        }
    }

    public void determineIfNPCSpawnsWhileSleeping() {
        double randomNumberForPossibleSpawnedNPC = Math.random() * 6;
        if (randomNumberForPossibleSpawnedNPC < 1) {
            npcSpawnsWhileSleeping = true;
        } else if (randomNumberForPossibleSpawnedNPC >= 1) {
            npcSpawnsWhileSleeping = false;
        }
    }

    public void determineAmountOfGoldInRoom() {
        gold = (int) (Math.random() * 100);
    }

    public void addBlockedRoomToList() {
        blockedRooms.add(blockedRoomNumber);
    }

    public int getBlockedRoomListSize() {
        return blockedRooms.size();
    }

    public int scanBlockedRoomsListForThisRoomNumber() {
        return blockedRooms.get(forLoopCounterForBlocked);
    }

    public void addExploredRoomToList() {
        exploredRooms.add(exploredRoomNumber);
    }

    public int getExploredRoomListSize() {
        return exploredRooms.size();
    }

    public int scanExploredRoomsListForThisRoomNumber() {
        return exploredRooms.get(forLoopCounterForExplored);
    }

    public int getDieRollForGold() {
        return dieRollForGold;
    }

    public void setDieRollForGold(int dieRollForGold) {
        this.dieRollForGold = dieRollForGold;
    }
}
