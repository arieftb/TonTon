package com.arieftb.tonton.utils;

import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.model.entity.TvShowEntity;

import java.util.ArrayList;

public class DataDummy {
    public static ArrayList<MovieEntity> generateMovie() {
        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(429617, "Spider-Man: Far from Home", "en", 7.8, "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg", "2019-06-28", "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent."));
        movies.add(new MovieEntity(399579, "Alita: Battle Angel", "en", 6.8, "/xRWht48C2V8XNfzvPehyClOvDni.jpg", "2019-01-31", "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."));
        movies.add(new MovieEntity(420818, "The Lion King", "en", 5.8, "dzBtMocZuJbjLOXvrl4zGYigDzh.jpg", "2019-07-12", "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his."));
        movies.add(new MovieEntity(301528, "Toy Story 4", "en", 7.8, "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg", "2019-06-19", "PWoody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy."));

        return movies;
    }

    public static ArrayList<TvShowEntity> generateTvShows() {
        ArrayList<TvShowEntity> tvShows = new ArrayList<>();

        tvShows.add(new TvShowEntity(11634, "See No Evil: The Moors Murders", "en", 5.5, "/b71BaRjp9TwxUZodLGgSRIlkfL8.jpg", "2006-05-14", "The dramatisation of one of the most notorious killing sprees in British history."));

        tvShows.add(new TvShowEntity(60735, "The Flash", "en", 6.7, "/fki3kBlwJzFp8QohL43g9ReV455.jpg", "2014-10-07", "TAfter a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."));

        tvShows.add(new TvShowEntity(66732, "Stranger Things", "en", 8.3, "//x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg", "2016-07-15", "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl."));

        tvShows.add(new TvShowEntity(456, "The Simpsons", "en", 7.1, "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg", "1989-12-17", "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general."));

        return tvShows;
    }
}
