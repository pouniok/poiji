package com.poiji.internal;

import static com.poiji.util.PoijiConstants.DEFAULT_DATE_PATTERN;

/**
 * Created by hakan on 17/01/2017.
 */
public final class PoijiOptions {

    private int skip;
    private String datePattern;
    private boolean importFormattedCells;

    private PoijiOptions() {
        super();
    }

    private PoijiOptions setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    private PoijiOptions setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    public String datePattern() {
        return datePattern;
    }

    private PoijiOptions setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    public PoijiOptions setImportFormattedCells(boolean importFormattedCells) {
        this.importFormattedCells = importFormattedCells;
        return this;
    }

    public boolean importFormattedCells() {
        return importFormattedCells;
    }

    /**
     * the number of skipped rows
     *
     * @return n rows skipped
     */
    public int skip() {
        return skip;
    }

    public static class PoijiOptionsBuilder {

        private int skip = 1;
        private String datePattern = DEFAULT_DATE_PATTERN;
        private boolean importFormattedCells = IMPORT_FORMATTED_CELLS;

        private PoijiOptionsBuilder() {
        }

        private PoijiOptionsBuilder(int skip) {
            this.skip = skip;
        }

        public PoijiOptions build() {
            return new PoijiOptions().setSkip(skip).setDatePattern(datePattern)
                  .setImportFormattedCells(importFormattedCells);
        }

        public static PoijiOptionsBuilder settings() {
            return new PoijiOptionsBuilder();
        }

        public PoijiOptionsBuilder setDatePattern(String datePattern) {
            this.datePattern = datePattern;
            return this;
        }

        public PoijiOptionsBuilder setImportFormattedCells(boolean importFormattedCells) {
            this.importFormattedCells = importFormattedCells;
            return this;
        }

        public PoijiOptionsBuilder setSkip(int skip) {
            this.skip = skip;
            return this;
        }

        /**
         * Skip the n rows of the excel data. Default is 1
         *
         * @param skip ignored row number
         * @return builder itself
         */
        public static PoijiOptionsBuilder settings(int skip) {
            return new PoijiOptionsBuilder(skip);
        }

    }
}
