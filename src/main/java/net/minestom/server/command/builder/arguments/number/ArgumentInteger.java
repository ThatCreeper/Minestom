package net.minestom.server.command.builder.arguments.number;

import org.jetbrains.annotations.NotNull;

public class ArgumentInteger extends ArgumentNumber<Integer> {

    public ArgumentInteger(String id) {
        super(id);
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }

    @Override
    public int getCorrectionResult(@NotNull String value) {
        try {
            Integer.parseInt(parseValue(value), getRadix(value));
            return SUCCESS;
        } catch (NumberFormatException | NullPointerException e) {
            return NOT_NUMBER_ERROR;
        }
    }

    @NotNull
    @Override
    public Integer parse(@NotNull String value) {
        return Integer.parseInt(parseValue(value), getRadix(value));
    }

    @Override
    public int getConditionResult(@NotNull Integer value) {
        // Check range
        if (hasMin && value < min) {
            return RANGE_ERROR;
        }
        if (hasMax && value > max) {
            return RANGE_ERROR;
        }

        return SUCCESS;
    }

}
