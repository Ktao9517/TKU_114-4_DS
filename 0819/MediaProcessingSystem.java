public class MediaProcessingSystem {

    abstract static class MediaFile {
        protected String fileName;
        protected long sizeInBytes;

        public MediaFile(String fileName, long sizeInBytes) {
            this.fileName = (fileName == null || fileName.trim().isEmpty()) ? "Unknown" : fileName.trim();
            this.sizeInBytes = Math.max(sizeInBytes, 0);
        }

        public abstract String getType();

        public void showInfo() {
            System.out.println(getType() + ": " + fileName + " (" + sizeInBytes + " bytes)");
        }
    }

    interface Playable {
        void play();
    }

    interface Compressible {
        void compress();
    }

    static class ImageFile extends MediaFile implements Compressible {
        public ImageFile(String fileName, long sizeInBytes) {
            super(fileName, sizeInBytes);
        }

        @Override
        public String getType() {
            return "Image";
        }

        @Override
        public void compress() {
            System.out.println("壓縮圖片 " + fileName + " → 減少約 40% 大小");
        }
    }

    static class AudioFile extends MediaFile implements Playable, Compressible {
        public AudioFile(String fileName, long sizeInBytes) {
            super(fileName, sizeInBytes);
        }

        @Override
        public String getType() {
            return "Audio";
        }

        @Override
        public void play() {
            System.out.println("播放音訊 " + fileName + "...");
        }

        @Override
        public void compress() {
            System.out.println("壓縮音訊 " + fileName + " → 轉換為較低位元率");
        }
    }

    static class VideoFile extends MediaFile implements Playable, Compressible {
        public VideoFile(String fileName, long sizeInBytes) {
            super(fileName, sizeInBytes);
        }

        @Override
        public String getType() {
            return "Video";
        }

        @Override
        public void play() {
            System.out.println("播放影片 " + fileName + "...");
        }

        @Override
        public void compress() {
            System.out.println("壓縮影片 " + fileName + " → 降低解析度與位元率");
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 媒體檔案處理 =====");

        MediaFile[] files = {
            new ImageFile("vacation.jpg", 3500000),
            new AudioFile("podcast.mp3", 12000000),
            new VideoFile("lecture.mp4", 250000000),
            new ImageFile("logo.png", 45000)
        };

        for (MediaFile f : files) {
            f.showInfo();
            if (f instanceof Playable playable) {
                playable.play();
            }
            if (f instanceof Compressible compressible) {
                compressible.compress();
            }
            System.out.println();
        }
    }
}
