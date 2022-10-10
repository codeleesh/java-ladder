package nextstep.ladder.dto;

import nextstep.ladder.domain.Point;

public class PointDto {

    private final int index;
    private final boolean right;

    public PointDto(final int index, final boolean right) {

        this.index = index;
        this.right = right;
    }

    public static PointDto from(final Point point) {

        return new PointDto(point.getIndex(), point.getMovement().isRight());
    }

    public int getIndex() {

        return index;
    }

    public boolean isRight() {

        return right;
    }

    @Override
    public String toString() {
        return "PointDto{" +
                "index=" + index +
                ", right=" + right +
                '}';
    }
}
