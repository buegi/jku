package ss19.ue10.memecreator.factory;

import ss19.ue10.memecreator.model.Meme;

public interface TextDecoratorFactory {

    Meme createTextDecorator(Meme meme, String text, boolean top);

    Meme createShadowTextDecorator(Meme meme, String text, boolean top);
}