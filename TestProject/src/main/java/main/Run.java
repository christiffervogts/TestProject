package main;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import java.nio.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Run {

	private long window;

	int count = 99;
	float rand1, rand2, rand3, rand4;
	public Run(){
		init();
		loop();
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public void init() {
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!glfwInit()) {
			throw new IllegalStateException("Can't initialize");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(300, 300, "HEllO WORLD", NULL, NULL);
		if(window == NULL) {
			throw new RuntimeException("Faild to make window");
		}
		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
				
				glfwSetWindowShouldClose(window, true);
				
			}
		});
		
		try(MemoryStack stack = stackPush()){
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);
			
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0))/2, (vidmode.height() - pHeight.get(0))/2);
			
		}
			glfwMakeContextCurrent(window);
			
			glfwSwapInterval(1);
			
			glfwShowWindow(window);
		
	}
	public void loop() {
		GL.createCapabilities();

		while (!glfwWindowShouldClose(window)) {
			count++;
			if(count == 100) {
			rand1 = (float) Math.random();
			rand2 = (float) Math.random();
			rand3 = (float) Math.random();
			rand4 = (float) Math.random();
			count = 0;
			}
			
			glClearColor(rand1, rand2, rand3, rand4);
		    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		    glBegin(GL_TRIANGLES);
		    glColor3f(1.0f, 0.0f, 0.0f);
		    glVertex2f(-0.5f, -0.5f);

		    glColor3f(0.0f, 1.0f, 0.0f);
		    glVertex2f(0.5f, -0.5f);

		    glColor3f(0.0f, 0.0f, 1.0f);
		    glVertex2f(0.0f, 0.5f);
		    glEnd();

		    glfwSwapBuffers(window);
		    glfwPollEvents();
		}
	}
	
	
}
