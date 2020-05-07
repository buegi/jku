package swe2.ss19.ue10.memecreator.factory;

import swe2.ss19.ue10.memecreator.model.Meme;

public interface TextDecoratorFactory {

    Meme createTextDecorator(Meme meme, String text, boolean top);

    Meme createShadowTextDecorator(Meme meme, String text, boolean top);
}