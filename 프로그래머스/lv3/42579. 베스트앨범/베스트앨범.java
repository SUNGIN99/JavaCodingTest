import java.util.*;


class Solution {
    
    public static class Song implements Comparable<Song> {
        int plays;
        String genre;
        int index;


        public Song(int plays, String genre, int index) {
            this.plays = plays;
            this.genre = genre;
            this.index = index;
        }

        @Override
        public int compareTo(Song s) {
            if(this.plays < s.plays) return 1;
            else if (this.plays > s.plays) return -1;
            return 0;
        }
    }
    public static int[] solution(String[] genres, int[] plays) {

        HashMap<String, PriorityQueue<Song>> playlist = new HashMap<>();
        HashMap<String, Integer> played = new HashMap<>();

        int len = genres.length;

        // 장르, 플레이어 길이 최대 만개
        // O(nlogn) 정렬
        for(int i = 0; i<len ;i++){
            if(!playlist.containsKey(genres[i])){
                PriorityQueue<Song> songList = new PriorityQueue<>();
                songList.add(new Song(plays[i], genres[i], i));
                playlist.put(genres[i], songList);

                played.put(genres[i], plays[i]);

            }else{
                PriorityQueue<Song> songList = playlist.get(genres[i]);
                songList.add(new Song(plays[i], genres[i], i));
                playlist.put(genres[i], songList);

                int genPlayed = played.get(genres[i]) + plays[i];
                played.put(genres[i], genPlayed);

            }
        }

        PriorityQueue<Song> mostPlayed = new PriorityQueue<>();
        for(Map.Entry<String, Integer> song : played.entrySet()){
            mostPlayed.add(new Song(song.getValue(), song.getKey(), 0));
        }
        ArrayList<Integer> result  = new ArrayList<>();

        while (!mostPlayed.isEmpty()){
            Song song = mostPlayed.poll();
            if (song == null){
                break;
            }
            int i = 0;
            PriorityQueue<Song> songsByGenre = playlist.get(song.genre);
            while(i<2){
                Song s = songsByGenre.poll();
                if (s == null){
                    break;
                }
                result.add(s.index);
                i++;
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }
}