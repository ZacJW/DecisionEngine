package DecisionEngine.Render;

/**
 * The interface all DecisionEngine renderers must implement
 */
public interface RendererInterface {
    
    /**
     * Initialises the renderer, including creating any windows needed to display the output
     */
    public void initialise();

    /**
     * 
     * @return Whether the program should terminate due to the user requesting the application to close.
     */
    public boolean shouldTerminate();

    /**
     * Poll window events
     */
    public void pollEvents();

    /**
     * Stops the renderer, closes any windows created by it, and frees up resources consumed by it
     */
    public void terminate();

    /**
     * Renders all render layers on the renderer and displays the resulting composite to the window
     */
    public void renderAll();
}
