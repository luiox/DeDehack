package me.caiyudu.dedehack.util.imgs;

import java.awt.image.BufferedImage;

public class ImageFrame
{
    private final int delay;
    private final BufferedImage image;
    private final String dispodede;
    private final int width, height;

    public ImageFrame(BufferedImage image, int delay, String dispodede, int width, int height)
    {
        this.image = image;
        this.delay = delay;
        this.dispodede = dispodede;
        this.width = width;
        this.height = height;
    }

    public ImageFrame(BufferedImage image)
    {
        this.image = image;
        this.delay = -1;
        this.dispodede = null;
        this.width = -1;
        this.height = -1;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public int getDelay()
    {
        return delay;
    }

    public String getDispodede()
    {
        return dispodede;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
