import { z } from 'zod'

export const loginSchema = z.object({
    username: z
        .string()
        .min(5, { message: 'Username must be at least 5 characters' })
        .max(16, { message: "Username can't be more than 16 characters" }),
    password: z.string().min(8).max(64)
})

export type LoginSchema = typeof loginSchema
