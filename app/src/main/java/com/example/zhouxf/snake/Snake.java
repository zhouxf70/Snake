package com.example.zhouxf.snake;

public class Snake {

    /**
     * 食物坐标
     */
    private Node food;

    private Node header;

    private Node tail;

    private Direction dir;

    private final int width;
    private final int height;
    private int length;

    public Snake(int width, int height) {
        this.width = width;
        this.height = height;
        header = new Node(width / 2, height / 2);
        tail = header;
        // 当只有一个节点时，该节点prev为null
//        tail.prev = header;
        dir = Direction.BOTTOM;
        length++;
        this.food = newFood();
    }

    /**
     * 向{@link Direction}方向进一步
     *
     * @return true：蛇头与身体相遇
     */
    public boolean forward() {
        int x = header.x + dir.x;
        int y = header.y + dir.y;

        if (x == 0) x = width;
        else if (x == width + 1) x = 1;

        if (y == 0) y = height;
        else if (y == height + 1) y = 1;

        // 创建新节点，并将新节点放在头部
        Node temp = new Node(x, y);
        // 长度不为1且包含新节点时相遇
        if (length != 1 && hasNode(temp))
            return true;

        if (length != 1) {
            header.prev = temp;
            header = temp;
            // 如果吃到食物，重新创建一个食物
            // 如果没吃到食物，移除末尾节点
            if (header.equals(food)) {
                food = newFood();
                length++;
            } else {
                temp = tail.prev;
                tail = temp;
            }
        } else {
            // 当只有一个节点时，该节点同时是header和tail，强制要求该节点prev为null
            header = temp;
            if (header.equals(food)) {
                food = newFood();
                tail.prev = header;
                length++;
            } else {
                tail = header;
            }
        }

        return false;
    }

//    private final Random random = Math.random();

    public Node newFood() {
        int x = (int) (Math.random() * width + 1);
        int y = (int) (Math.random() * height + 1);
        Node food = new Node(x, y);
        return hasNode(food) ? newFood() : food;
    }

    /**
     * 返回食物和蛇身节点数组
     * 食物节点位于数组第一位
     */
    public Node[] listNode() {
        Node[] nodes = new Node[length + 1];
        nodes[0] = food;
        Node temp = tail;
        int position = length;
        while (position > 0) {
            nodes[position--] = temp;
            temp = temp.prev;
        }
        return nodes;
    }

    private int getPosition(Node node) {
        return (node.x - 1) + (node.y - 1) * width;
    }

    /**
     * 检测蛇身是否包含某个节点
     *
     * @param node 新节点
     * @return true : 包含
     */
    private boolean hasNode(Node node) {
        Node temp = tail;
        while (temp != null) {
            if (temp.equals(node))
                return true;
            temp = temp.prev;
        }
        return false;
    }

    public boolean checkDir(Direction newDir) {
        return newDir.x * dir.x + newDir.y * dir.y != -1;
    }

    public Direction getDir() {
        return dir;
    }

    /**
     * 设置前进方向，同时前进一步
     *
     * @param newDir 新方向
     * @return true：前进一步后蛇头与蛇身相遇
     */
    public boolean setDir(Direction newDir) {
        // 相反方向的操作忽略
        if (!checkDir(newDir))
            return false;
        this.dir = newDir;
        return true;
    }

    public Node getFood() {
        return food;
    }


    @Deprecated
    public void setFood(int x, int y) {
        this.food = new Node(x, y);
    }

    static class Node {
        /**
         * 取值范围 1~width
         */
        int x;
        /**
         * 取值范围 1~height
         */
        int y;
        Node prev;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }


        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    enum Direction {

        TOP(0, -1), BOTTOM(0, 1), LEFT(-1, 0), RIGHT(1, 0);

        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            String dir = "";
            switch (this) {
                case TOP:
                    return "TOP";
                case BOTTOM:
                    return "BOTTOM";
                case LEFT:
                    return "LEFT";
                case RIGHT:
                    return "RIGHT";
            }
            return dir;
        }
    }


}
